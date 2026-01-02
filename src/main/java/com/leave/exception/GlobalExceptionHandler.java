package com.leave.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	public static class ApiError {
        public int status;
        public String error;
        public String message;
        public String timestamp;

        public ApiError(HttpStatus status, String message) {
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
            this.timestamp = LocalDateTime.now().toString();
        }
    }

	// 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 401
    @ExceptionHandler({BadCredentialsException.class, org.springframework.security.core.AuthenticationException.class})
    public ResponseEntity<ApiError> handleAuthentication(Exception ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED, "Invalid username or password"), HttpStatus.UNAUTHORIZED);
    }

    // 403
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.FORBIDDEN, "You do not have permission to access this resource"), HttpStatus.FORBIDDEN);
    }

    // 404
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNotFound(NoSuchElementException ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
