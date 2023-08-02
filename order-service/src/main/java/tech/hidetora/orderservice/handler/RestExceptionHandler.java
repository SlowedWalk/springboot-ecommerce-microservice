package tech.hidetora.orderservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.hidetora.orderservice.dto.reponse.ApiResponse;
import tech.hidetora.orderservice.exception.OrderNotFoundException;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse> handleException(OrderNotFoundException exception, WebRequest webRequest) {

        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ApiResponse errorDto = ApiResponse.builder()
                .httpStatus(notFound)
                .statusCode(notFound.value())
                .timestamp(Instant.now().toString())
                .success(Boolean.FALSE)
                .error(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

}
