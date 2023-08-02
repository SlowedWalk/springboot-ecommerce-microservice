package tech.hidetora.inventoryservice.enumeration;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value()),
    PRODUCT_ALREADY_EXIST(HttpStatus.CONFLICT.value());
    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
