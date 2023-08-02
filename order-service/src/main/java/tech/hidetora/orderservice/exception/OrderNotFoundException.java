package tech.hidetora.orderservice.exception;

import lombok.Getter;
import tech.hidetora.orderservice.enumeration.ErrorCodes;

public class OrderNotFoundException extends RuntimeException {
    @Getter
    private ErrorCodes errorCode;
}
