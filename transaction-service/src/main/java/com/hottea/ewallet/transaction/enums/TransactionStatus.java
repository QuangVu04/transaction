package com.hottea.ewallet.transaction.enums;


import lombok.Getter;

@Getter
public enum TransactionStatus {
    PENDING,
    FAILED,
    SUCCESS;
    public static boolean isValidTransactionStatus(String transactionStatus) {
        return "TIMEOUT".equalsIgnoreCase(transactionStatus) ||
                "SUCCESS".equalsIgnoreCase(transactionStatus) ||
                "FAIL".equalsIgnoreCase(transactionStatus);
    }

}
