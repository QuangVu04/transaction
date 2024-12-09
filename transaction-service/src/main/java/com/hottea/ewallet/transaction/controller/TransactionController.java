package com.hottea.ewallet.transaction.controller;

import com.hottea.ewallet.transaction.common.base.ResponseDataPaging;
import com.hottea.ewallet.transaction.dto.Request.DashboardStatRequest;
import com.hottea.ewallet.transaction.dto.Request.TransactionRequest;
import com.hottea.ewallet.transaction.dto.Request.TransactionSearchRequest;
import com.hottea.ewallet.transaction.dto.Respond.DashboardStatRespond;
import com.hottea.ewallet.transaction.dto.Respond.ResponseData;
import com.hottea.ewallet.transaction.dto.Respond.TransactionStatSendRespond;
import com.hottea.ewallet.transaction.entity.Transaction;
import com.hottea.ewallet.transaction.service.TransactionDashboardService;
import com.hottea.ewallet.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionsService;

    @Autowired
    private TransactionDashboardService transactionDashboardService;

    @PostMapping
    public ResponseData<Transaction> createTransaction(@RequestBody @Valid TransactionRequest request){
        return transactionsService.createTransactions(request);
    }

    @GetMapping("/{uuid}")
    public ResponseData<Transaction> getTransactionByUuid(@PathVariable String uuid) {
        return transactionsService.getTransactionByUuid(uuid);
    }

    @GetMapping("/history")
    public ResponseDataPaging<List<Transaction>> getTransactionHistory(
            @ModelAttribute TransactionSearchRequest request,
            @PageableDefault(size = 10) Pageable pageable) {
        return transactionsService.getTransactionHistory(request, pageable);
    }

    @GetMapping("/history/{walletId}")
    public ResponseDataPaging<List<Transaction>> getTransactionHistoryUser(
            @PathVariable("walletId") String walletid,  // Explicit mapping
            @ModelAttribute TransactionSearchRequest request,
            @PageableDefault(size = 10) Pageable pageable) {
        return transactionsService.getTransactionHistoryUser( request, pageable, walletid);
    }

    @GetMapping("/stat/{walletId}")
    public ResponseData<DashboardStatRespond> getDashboardMetrics(@PathVariable String walletId) {
        DashboardStatRequest request = new DashboardStatRequest();
        request.setWalletId(walletId);
        return transactionDashboardService.getDashboardMetrics(request);
    }



}
