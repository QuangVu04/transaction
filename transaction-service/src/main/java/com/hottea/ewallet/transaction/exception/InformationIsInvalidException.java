package com.hottea.ewallet.transaction.exception;

import com.hottea.ewallet.common.api.exception.BadRequestAlertException;
import com.hottea.ewallet.common.api.util.ErrorCode;

public class InformationIsInvalidException extends BadRequestAlertException {
    public InformationIsInvalidException() {
        super(ErrorCode.MSG1018);
    }
}
