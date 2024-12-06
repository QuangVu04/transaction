package com.hottea.ewallet.transaction.enums;

import com.hottea.ewallet.transaction.enums.message.LabelKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MSG1501  (LabelKey.ERROR_INSUFFICIENT_AMOUNT),;

    private String key;
}
