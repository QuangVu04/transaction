package com.hottea.ewallet.transaction.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatRequest {
    private String walletId;
    private Timestamp today;
    private Timestamp startOfWeek;
}
