//package com.hottea.ewallet.transaction.repository;
//
//import com.hottea.ewallet.transaction.entity.Transaction;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//
//public interface TransactionDashboardRepository extends JpaRepository<Transaction,Long> {
//    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.fromWalletId = :fromWalletId")
//    Long TotalTransaction(@Param("fromWalletId") String fromWalletId);
//    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.fromWalletId =:fromWalletId " +
//            "WHERE t.createdAt >= :startOfWeek AND t.createdAt <= :today")
//    BigDecimal WeeklyDeposits(@Param("fromWalletId") String fromWalletId,
//                              @Param("today") Timestamp today,
//                              @Param("startOfWeek") Timestamp startOfWeek);
//    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.toWalletId =:toWalletId " +
//            "WHERE t.createdAt >= :startOfWeek AND t.createdAt <= :today")
//    BigDecimal WeeklyWithdrawals(@Param("toWalletId") String toWalletId,
//                                 @Param("today") Timestamp today,
//                                 @Param("startOfWeek") Timestamp startOfWeek);
//}
