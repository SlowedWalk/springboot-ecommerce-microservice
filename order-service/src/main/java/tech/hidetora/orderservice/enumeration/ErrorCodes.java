package tech.hidetora.orderservice.enumeration;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value()),
    ORDER_ALREADY_EXIST(HttpStatus.CONFLICT.value()),
    PRODUCT_NOT_AVAILABLE(HttpStatus.BAD_REQUEST.value());
    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
