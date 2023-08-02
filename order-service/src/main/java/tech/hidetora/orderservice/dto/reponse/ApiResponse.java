package tech.hidetora.orderservice.dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(NON_NULL)
public class ApiResponse {
    private Boolean success;
    private String message;
    private HttpStatus httpStatus;
    private int statusCode;
    private String timestamp;
    private String error;
    private String errorCode;
    private Object data;
}
