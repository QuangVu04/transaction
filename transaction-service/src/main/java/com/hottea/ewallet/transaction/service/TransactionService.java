package com.hottea.ewallet.transaction.service;

import com.hottea.ewallet.transaction.common.base.ResponseDataPaging;
import com.hottea.ewallet.transaction.dto.Request.TransactionRequest;
import com.hottea.ewallet.transaction.dto.Request.TransactionSearchRequest;
import com.hottea.ewallet.transaction.dto.Respond.ResponseData;
import com.hottea.ewallet.transaction.entity.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    ResponseData<Transaction> createTransactions(TransactionRequest request);

    ResponseData<Transaction> getTransactionByUuid(String uuid);

    ResponseDataPaging<List<Transaction>> getTransactionHistory(TransactionSearchRequest request, Pageable pageable);
    ResponseDataPaging<List<Transaction>> getTransactionHistoryUser(TransactionSearchRequest request, Pageable pageable, String fromWalletId);

}
