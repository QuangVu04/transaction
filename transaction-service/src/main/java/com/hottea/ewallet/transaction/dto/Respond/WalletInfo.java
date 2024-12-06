package com.hottea.ewallet.transaction.dto.Respond;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletInfo {
    private String message;
    private int resstatus;
    private String localDateTime;
    private WalletData data; // This will hold the balance and status

    @Data
    public static class WalletData {
        private BigDecimal balance;
        private String currency; // If you want to keep this
        private String status;
        private String createdAt;
        private String updatedAt;
    }
}
