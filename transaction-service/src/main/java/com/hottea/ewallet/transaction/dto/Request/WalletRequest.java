package com.hottea.ewallet.transaction.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {
    private String walletId;
    private BigDecimal amount;
}
