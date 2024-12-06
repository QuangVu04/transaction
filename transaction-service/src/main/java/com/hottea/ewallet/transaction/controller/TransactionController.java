package com.hottea.ewallet.transaction.controller;

import com.hottea.ewallet.transaction.common.base.ResponseDataPaging;
import com.hottea.ewallet.transaction.dto.Request.TransactionRequest;
import com.hottea.ewallet.transaction.dto.Request.TransactionSearchRequest;
import com.hottea.ewallet.transaction.dto.Respond.RespondData;
import com.hottea.ewallet.transaction.dto.Respond.Response;
import com.hottea.ewallet.transaction.dto.Respond.TransactionRespond;
import com.hottea.ewallet.transaction.dto.Respond.TransactionStatSendRespond;
import com.hottea.ewallet.transaction.entity.Transaction;
import com.hottea.ewallet.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionsService;

    @PostMapping
    public RespondData<Transaction> createTransaction(@RequestBody @Valid TransactionRequest request){
        return transactionsService.createTransactions(request);
    }

    @GetMapping("/{uuid}")
    public RespondData<Transaction> getTransactionByUuid(@PathVariable String uuid) {
        return transactionsService.getTransactionByUuid(uuid);
    }

    @GetMapping("/history")
    public ResponseDataPaging<List<Transaction>> getTransactionHistory(
            @ModelAttribute TransactionSearchRequest request,
            @PageableDefault(size = 10) Pageable pageable) {
        return transactionsService.getTransactionHistory(request, pageable);
    }

    @GetMapping("/recent/{fromWalletId}")
    public List<TransactionStatSendRespond> transactionStatSend(@PathVariable String fromWalletId) {
        return transactionsService.TransactionStatSend(fromWalletId);
    }



}
