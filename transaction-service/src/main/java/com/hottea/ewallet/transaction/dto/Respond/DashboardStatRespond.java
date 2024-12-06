package com.hottea.ewallet.transaction.dto.Respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatRespond {
    private Long totalTransactions;
    private BigDecimal weeklyDeposits;
    private BigDecimal weeklyWithdrawals;

}
