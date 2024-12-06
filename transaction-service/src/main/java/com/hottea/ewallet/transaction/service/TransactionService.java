package com.hottea.ewallet.transaction.service;

import com.hottea.ewallet.transaction.common.base.ResponseDataPaging;
import com.hottea.ewallet.transaction.dto.Request.TransactionRequest;
import com.hottea.ewallet.transaction.dto.Request.TransactionSearchRequest;
import com.hottea.ewallet.transaction.dto.Respond.RespondData;
import com.hottea.ewallet.transaction.dto.Respond.TransactionStatSendRespond;
import com.hottea.ewallet.transaction.entity.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    RespondData<Transaction> createTransactions(TransactionRequest request);

    RespondData<Transaction> getTransactionByUuid(String uuid);

    ResponseDataPaging<List<Transaction>> getTransactionHistory(TransactionSearchRequest request, Pageable pageable);

}
