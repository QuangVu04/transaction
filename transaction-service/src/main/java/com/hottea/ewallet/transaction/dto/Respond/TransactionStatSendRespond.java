package com.hottea.ewallet.transaction.dto.Respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatSendRespond {
    private String walletId;
    private String transactionId;
    private BigDecimal amount;
    private Timestamp createdAt;
}
