package tech.hidetora.productservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.hidetora.productservice.exception.InvalidOperationException;
import tech.hidetora.productservice.exception.ProductNotFoundException;
import tech.hidetora.productservice.dto.ApiResponse;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse> handleException(ProductNotFoundException exception, WebRequest webRequest) {

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


    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ApiResponse> handleException(InvalidOperationException exception, WebRequest webRequest) {

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ApiResponse errorDto = ApiResponse.builder()
                .httpStatus(badRequest)
                .statusCode(badRequest.value())
                .timestamp(Instant.now().toString())
                .success(Boolean.FALSE)
                .error(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }

    /*
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .code(ErrorCodes.BAD_CREDENTIALS)
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login et / ou mot de passe incorrecte"))
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }*/
}
