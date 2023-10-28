package com.ecommerce.walmart.model;

import lombok.Data;

@Data
public class ServerResponse {
    private String message;
    private int status;
    public ServerResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
