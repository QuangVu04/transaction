package com.hottea.ewallet.transaction.service.impl;

import com.hottea.ewallet.common.messages.LabelKey;
import com.hottea.ewallet.transaction.common.config.message.MessageKey;
import com.hottea.ewallet.transaction.dto.Request.DashboardStatRequest;
import com.hottea.ewallet.transaction.dto.Respond.DashboardStatRespond;
import com.hottea.ewallet.transaction.dto.Respond.ResponseData;
import com.hottea.ewallet.transaction.dto.Respond.TransactionStatSendRespond;
import com.hottea.ewallet.transaction.entity.Transaction;
import com.hottea.ewallet.transaction.repository.TransactionDashboardRepository;
import com.hottea.ewallet.transaction.service.TransactionDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionDashboardServiceImpl implements TransactionDashboardService {

    @Autowired
    private TransactionDashboardRepository transactionDashboardRepository;

    public ResponseData<DashboardStatRespond> getDashboardMetrics(DashboardStatRequest request) {
        DashboardStatRespond dashboardStatRespond = new DashboardStatRespond();

        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp startOfWeek = Timestamp.valueOf(LocalDateTime.now().minus(7, ChronoUnit.DAYS));

        dashboardStatRespond.setTotalTransactions(
                transactionDashboardRepository.TotalTransaction(request.getWalletId())
        );

        BigDecimal weeklyDeposits = transactionDashboardRepository.WeeklyDeposits(
                request.getWalletId(), today, startOfWeek
        );

        dashboardStatRespond.setWeeklyDeposits(weeklyDeposits != null ? weeklyDeposits : BigDecimal.ZERO);

        BigDecimal weeklyWithdrawals = transactionDashboardRepository.WeeklyWithdrawals(
                request.getWalletId(), today, startOfWeek
        );
        dashboardStatRespond.setWeeklyWithdrawals(weeklyWithdrawals != null ? weeklyWithdrawals : BigDecimal.ZERO);

        List<Transaction> sentTransactions = transactionDashboardRepository.TransactionStatSend(
                request.getWalletId()
        );
        List<TransactionStatSendRespond> sentTransactionStats = sentTransactions.stream()
                .map(transaction -> new TransactionStatSendRespond(
                        transaction.getToWalletId(),
                        transaction.getUuid(),
                        transaction.getAmount(),
                        transaction.getCreatedAt()
                ))
                .collect(Collectors.toList());
        dashboardStatRespond.setSentTransactions(sentTransactionStats);

        List<Transaction> receivedTransactions = transactionDashboardRepository.TransactionStatReceived(
                request.getWalletId()
        );
        List<TransactionStatSendRespond> receivedTransactionStats = receivedTransactions.stream()
                .map(transaction -> new TransactionStatSendRespond(
                        transaction.getToWalletId(),
                        transaction.getUuid(),
                        transaction.getAmount(),
                        transaction.getCreatedAt()
                ))
                .collect(Collectors.toList());
        dashboardStatRespond.setReceivedTransactions(receivedTransactionStats);

        return new ResponseData<>(dashboardStatRespond, HttpStatus.OK, MessageKey.STAT_FOUND);
    }
}
