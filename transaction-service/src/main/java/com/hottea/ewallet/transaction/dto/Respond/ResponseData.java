package com.hottea.ewallet.transaction.dto.Respond;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseData<T>{
    private T data;
    private HttpStatus respondCode;
    private String message;

    public ResponseData(T data, HttpStatus respondCode, String message){
        this.data = data;
        this.respondCode = respondCode;
        this.message = message;
    }

    public ResponseData(HttpStatus respondCode, String message){
        this.respondCode = respondCode;
        this.message = message;
    }
}
