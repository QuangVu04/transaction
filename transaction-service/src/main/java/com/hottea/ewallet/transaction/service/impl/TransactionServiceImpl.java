package com.hottea.ewallet.transaction.service.impl;

import com.hottea.ewallet.common.api.exception.BadRequestAlertException;
import com.hottea.ewallet.common.api.util.ErrorCode;
import com.hottea.ewallet.common.messages.LabelKey;
import com.hottea.ewallet.transaction.clients.WalletClient;
import com.hottea.ewallet.transaction.common.base.ResponseDataPaging;
import com.hottea.ewallet.transaction.dto.Request.TransactionRequest;
import com.hottea.ewallet.transaction.dto.Request.TransactionSearchRequest;
import com.hottea.ewallet.transaction.dto.Request.WalletRequest;
import com.hottea.ewallet.transaction.dto.Respond.*;
import com.hottea.ewallet.transaction.entity.Transaction;
import com.hottea.ewallet.transaction.exception.InsufficientAmmountException;
import com.hottea.ewallet.transaction.exception.TransactionNotFoundException;
import com.hottea.ewallet.transaction.repository.TransactionRepository;
import com.hottea.ewallet.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletClient walletClient;

    @Override
    public RespondData<Transaction> createTransactions(TransactionRequest request) {
        String transactionStatus = request.getTransactionStatus();

        // Validate wallets and amounts
        WalletInfo fromWallet = walletClient.getBalanceById(request.getFromWalletId());
        WalletInfo toWallet = walletClient.getBalanceById(request.getToWalletId());

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestAlertException(ErrorCode.MSG1109);
        }

        if (fromWallet.getData().getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientAmmountException();
        }

        // Process wallet withdrawals and deposits
        WalletRequest withdrawRequest = new WalletRequest();
        withdrawRequest.setWalletId(request.getFromWalletId());
        withdrawRequest.setAmount(request.getAmount());
        walletClient.walletWithdraw(withdrawRequest);

        WalletRequest depositRequest = new WalletRequest();
        depositRequest.setWalletId(request.getToWalletId());
        depositRequest.setAmount(request.getAmount());
        walletClient.walletDeposit(depositRequest);

        // Save transaction details
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setFromWalletId(request.getFromWalletId());
        transaction.setToWalletId(request.getToWalletId());
        transaction.setTransactionStatus(transactionStatus);
        transactionRepository.save(transaction);

        return new RespondData<>(transaction,HttpStatus.OK, LabelKey.TRANSACTION_CREATED_SUCCESSFULLY);
    }

    public RespondData<Transaction> getTransactionByUuid(String uuid) {
        Transaction transaction = transactionRepository.findByUuid(uuid)
                .orElseThrow(TransactionNotFoundException::new);

        return new RespondData<>(transaction,HttpStatus.FOUND, LabelKey.TRANSACTION_FOUND);
    }

    public ResponseDataPaging<List<Transaction>> getTransactionHistory(TransactionSearchRequest request, Pageable pageable) {
        Page<Transaction> transactionsPage = transactionRepository.findTransactions(request, pageable);

        ResponseDataPaging<List<Transaction>> response = new ResponseDataPaging<>();
        response.setData(transactionsPage.getContent());
        response.setCurrentPage(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotalItems(transactionsPage.getTotalElements());
        response.setTotalPages(transactionsPage.getTotalPages());

        return response;
    }

    public List<TransactionStatSendRespond> TransactionStatSend(String fromWalletId) {
        List<Transaction> transactions = transactionRepository.TransactionStatSend(fromWalletId);

        return transactions.stream()
                .map(transaction -> new TransactionStatSendRespond(
                        transaction.getToWalletId(),
                        transaction.getUuid(),
                        transaction.getAmount(),
                        transaction.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
