package com.hottea.ewallet.transaction.exception;


import com.hottea.ewallet.transaction.enums.ErrorCode;

public class InsufficientAmmountException extends RuntimeException{
    public InsufficientAmmountException(){super(ErrorCode.MSG1501.getKey());}
}
