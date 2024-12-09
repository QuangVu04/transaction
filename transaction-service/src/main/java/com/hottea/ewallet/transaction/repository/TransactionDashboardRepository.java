package com.hottea.ewallet.transaction.repository;

import com.hottea.ewallet.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionDashboardRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.fromWalletId = :fromWalletId OR t.toWalletId = :fromWalletId")
    Long TotalTransaction(@Param("fromWalletId") String fromWalletId);
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.fromWalletId =:fromWalletId " +
            "AND t.createdAt >= :startOfWeek AND t.createdAt <= :today")
    BigDecimal WeeklyDeposits(@Param("fromWalletId") String fromWalletId,
                              @Param("today") Timestamp today,
                              @Param("startOfWeek") Timestamp startOfWeek);
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.toWalletId =:fromWalletId " +
            "AND t.createdAt >= :startOfWeek AND t.createdAt <= :today")
    BigDecimal WeeklyWithdrawals(@Param("fromWalletId") String fromWalletId,
                                 @Param("today") Timestamp today,
                                 @Param("startOfWeek") Timestamp startOfWeek);

    @Query("SELECT t FROM Transaction t WHERE t.fromWalletId = :fromWalletId ORDER BY t.createdAt DESC")
    List<Transaction> TransactionStatSend(@Param("fromWalletId") String fromWalletId);

    @Query("SELECT t FROM Transaction t WHERE t.toWalletId = :fromWalletId ORDER BY t.createdAt DESC")
    List<Transaction> TransactionStatReceived(@Param("fromWalletId") String fromWalletId);

}
