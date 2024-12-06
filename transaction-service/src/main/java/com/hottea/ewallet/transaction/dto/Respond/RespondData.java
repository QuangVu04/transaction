package com.hottea.ewallet.transaction.dto.Respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
public class RespondData <T>{
    private T data;
    private HttpStatus respondCode;
    private String message;

    public RespondData(T data, HttpStatus respondCode, String message){
        this.data = data;
        this.respondCode = respondCode;
        this.message = message;
    }

    public RespondData(HttpStatus respondCode, String message){
        this.respondCode = respondCode;
        this.message = message;
    }
}
