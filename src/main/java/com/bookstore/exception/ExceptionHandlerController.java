package com.bookstore.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    /**
     * Handles authentication-related exceptions, such as when a user provides invalid credentials or an expired refresh token.
     * Returns an HTTP 401 UNAUTHORIZED response with a custom error message.
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) {
        return buildResponseEntity(HttpStatus.CONFLICT, "Error processing request", request);
    }

    /**
     * Builds a custom response entity with the given HTTP status, error message, and web request description.
     */
    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, WebRequest request) {
        // Build custom error response body
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getDescription(false).substring(4)) // removing "uri=" prefix
                .build();
        // Return response entity with custom error body and HTTP status
        return new ResponseEntity<>(errorResponse, status);
    }

}

/**
 * Custom error response body with timestamp, HTTP status code, error message, and the path of the request that caused the error.
 */
@Data
@Builder
class CustomErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
