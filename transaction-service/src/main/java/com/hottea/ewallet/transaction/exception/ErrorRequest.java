package com.hottea.ewallet.transaction.exception;

public class ErrorRequest extends GlobalException{
    public ErrorRequest(String errorCode, String message){
        super(errorCode, message);
    }
}
