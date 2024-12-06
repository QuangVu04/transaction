package com.hottea.ewallet.transaction.dto.Respond;

import com.hottea.ewallet.transaction.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRespond {
    private HttpStatus respondCode;
    private String message;
    private Transaction transaction;



}
