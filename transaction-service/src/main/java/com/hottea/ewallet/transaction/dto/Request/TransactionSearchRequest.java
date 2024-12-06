package com.hottea.ewallet.transaction.dto.Request;

import com.hottea.ewallet.common.messages.LabelKey;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
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
    @DecimalMin(value = "0.0", inclusive = false, message = LabelKey.ERROR_AMOUNT_IS_REQUIRED )
    private BigDecimal amount;
    private Timestamp startDate;
    private Timestamp endDate;
}
