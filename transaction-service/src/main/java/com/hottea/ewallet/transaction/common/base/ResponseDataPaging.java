package com.hottea.ewallet.transaction.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataPaging<T> {
    private T data;
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;

}
