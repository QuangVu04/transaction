package com.hottea.ewallet.transaction.exception;

import com.hottea.ewallet.common.api.exception.BadRequestAlertException;
import com.hottea.ewallet.common.api.util.ErrorCode;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super(ErrorCode.MSG1038.getKey());
    }
}
