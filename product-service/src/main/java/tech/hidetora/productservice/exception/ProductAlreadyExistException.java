package tech.hidetora.productservice.exception;

import lombok.Getter;
import tech.hidetora.productservice.enumeration.ErrorCodes;

public class ProductAlreadyExistException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode;

    public ProductAlreadyExistException(String message) {
        super(message);
    }

    public ProductAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ProductAlreadyExistException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
