package com.hottea.ewallet.transaction.enums.message;

import com.hottea.ewallet.transaction.common.config.message.MessageKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {
    MSG1501(MessageKey.ERROR_ACCOUNT_NUMBER_IS_REQUIRED),
    MSG1502(MessageKey.ERROR_INVALID_AMOUNT);

    private String key;
}
