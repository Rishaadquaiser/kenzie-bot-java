package com.kenzie.error;

import com.kenzie.error.exception.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestErrorHandler {
    public static final String USER_NOT_FOUND = "User not found";
    public static final String FRIEND_NOT_FOUND = "Friend not found";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String DATABASE_ERROR = "Database error";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access";
    public static final String FORBIDDEN_ACCESS = "Forbidden access";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDTO handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        return ErrorDTO.builder()
                .summary(INVALID_REQUEST)
                .description(e.getMessage())
                .statusCode("400")
                .build();
    }
}
