package com.hottea.ewallet.transaction.dto.Request;

import com.hottea.ewallet.common.messages.LabelKey;
import com.hottea.ewallet.transaction.common.config.message.MessageKey;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class  TransactionRequest {
    String fromWalletId;

    @NotBlank(message = MessageKey.ERROR_ACCOUNT_NUMBER_IS_REQUIRED)
    String toWalletId;

    @DecimalMin(value = "0.0", inclusive = false, message = LabelKey.ERROR_AMOUNT_IS_REQUIRED )
    BigDecimal amount;

    String transactionStatus;
    @NotBlank(message = MessageKey.VALIDATION_DESCRIPTION_NOT_BLANK )
    String description;
}
