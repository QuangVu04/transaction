package com.hottea.ewallet.transaction.repository;

import com.hottea.ewallet.transaction.dto.Request.TransactionSearchRequest;
import com.hottea.ewallet.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByUuid(String uuid);
    @Query("SELECT t FROM Transaction t WHERE " +
           " (:#{#request.fromWalletId} IS NULL OR t.fromWalletId = :#{#request.fromWalletId}) " +
            "AND (:#{#request.transactionStatus} IS NULL OR t.transactionStatus = :#{#request.transactionStatus}) " +
            "AND (:#{#request.toWalletId} IS NULL OR t.toWalletId = :#{#request.toWalletId}) " +
            "AND (:#{#request.amount} IS NULL OR t.amount = :#{#request.amount}) " +
            "AND (:#{#request.startDate} IS NULL OR t.createdAt >= :#{#request.startDate}) " +
            "AND (:#{#request.endDate} IS NULL OR t.createdAt <= :#{#request.endDate})"+
            "ORDER BY t.createdAt DESC")
    Page<Transaction> findTransactions(@Param("request") TransactionSearchRequest request, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE " +
            " (:fromWalletId IS NULL OR t.fromWalletId = :fromWalletId OR t.toWalletId = :fromWalletId) " +
            "AND (:#{#request.transactionStatus} IS NULL OR t.transactionStatus = :#{#request.transactionStatus}) " +
            "AND (:#{#request.toWalletId} IS NULL OR t.toWalletId = :#{#request.toWalletId}) " +
            "AND (:#{#request.amount} IS NULL OR t.amount = :#{#request.amount}) " +
            "AND (:#{#request.startDate} IS NULL OR t.createdAt >= :#{#request.startDate}) " +
            "AND (:#{#request.endDate} IS NULL OR t.createdAt <= :#{#request.endDate}) " +
            "ORDER BY t.createdAt DESC")
    Page<Transaction> findTransactionsUser(
            @Param("fromWalletId") String fromWalletId,
            @Param("request") TransactionSearchRequest request,
            Pageable pageable);

}
