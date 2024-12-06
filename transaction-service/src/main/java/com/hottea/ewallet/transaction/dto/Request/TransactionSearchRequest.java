package com.hottea.ewallet.transaction.dto.Request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSearchRequest {
    private String transactionStatus;
    private String fromWalletId;
    private String toWalletId;
    private BigDecimal amount;
    private Timestamp startDate;
    private Timestamp endDate;
}
