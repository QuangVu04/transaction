//package com.hottea.ewallet.transaction.exception;
//
//import com.hottea.ewallet.common.api.exception.BadRequestAlertException;
//import com.hottea.ewallet.common.api.util.ErrorCode;
//import feign.Response;
//import feign.codec.ErrorDecoder;
//
//public class FeignException implements ErrorDecoder {
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        return switch (response.status()) {
//            case 400 -> new InsufficientAmmountException(); // Bad Request
//            case 404 -> new InsufficientAmmountException(); // Not Found
//            case 500 -> new InsufficientAmmountException(); // Internal Server Error
//            default -> new InsufficientAmmountException();
//        };
//    }
//}
