package com.antra.iot.iotuserservice.exception;

public class CustomerException extends RuntimeException{
    private String reason;

    public CustomerException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
