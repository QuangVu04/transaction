package com.hottea.ewallet.transaction.service.impl;

import com.hottea.ewallet.common.api.exception.BadRequestAlertException;
import com.hottea.ewallet.common.api.util.ErrorCode;
import com.hottea.ewallet.common.messages.LabelKey;
import com.hottea.ewallet.transaction.clients.WalletClient;
import com.hottea.ewallet.transaction.common.base.ResponseDataPaging;
import com.hottea.ewallet.transaction.common.config.message.MessageKey;
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

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletClient walletClient;

    @Override
    public ResponseData<Transaction> createTransactions(TransactionRequest request) {

        WalletInfo fromWallet = walletClient.getBalanceById(request.getFromWalletId());
        WalletInfo toWallet = walletClient.getBalanceById(request.getToWalletId());


        if (fromWallet.getData().getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientAmmountException();
        }

        WalletRequest withdrawRequest = new WalletRequest();
        withdrawRequest.setWalletId(request.getFromWalletId());
        withdrawRequest.setAmount(request.getAmount());
        walletClient.walletWithdraw(withdrawRequest);


        WalletRequest depositRequest = new WalletRequest();
        depositRequest.setWalletId(request.getToWalletId());
        depositRequest.setAmount(request.getAmount());
        walletClient.walletDeposit(depositRequest);


        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setFromWalletId(request.getFromWalletId());
        transaction.setToWalletId(request.getToWalletId());
        transaction.setTransactionStatus("COMPLETED");
        transactionRepository.save(transaction);

        return new ResponseData<>(transaction,HttpStatus.OK, MessageKey.TRANSACTION_CREATED_SUCCESSFULLY);
    }

    public ResponseData<Transaction> getTransactionByUuid(String uuid) {
        Transaction transaction = transactionRepository.findByUuid(uuid)
                .orElseThrow(TransactionNotFoundException::new);

        return new ResponseData<>(transaction,HttpStatus.FOUND, MessageKey.TRANSACTION_FOUND);
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

    public ResponseDataPaging<List<Transaction>> getTransactionHistoryUser(TransactionSearchRequest request, Pageable pageable, String fromWalletId) {
        Page<Transaction> transactionsPage = transactionRepository.findTransactionsUser(fromWalletId, request, pageable);

        ResponseDataPaging<List<Transaction>> response = new ResponseDataPaging<>();
        response.setData(transactionsPage.getContent());
        response.setCurrentPage(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotalItems(transactionsPage.getTotalElements());
        response.setTotalPages(transactionsPage.getTotalPages());

        return response;
    }

}
