package tech.hidetora.productservice.exception;

import lombok.Getter;
import tech.hidetora.productservice.enumeration.ErrorCodes;

public class ProductNotFoundException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ProductNotFoundException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
