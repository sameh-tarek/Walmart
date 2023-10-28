package com.ecommerce.walmart.exception;

import lombok.Data;

@Data
public class ExceptionBody {
    private String message;
    private int status;
    public ExceptionBody(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
