package com.denmiagkov.meter.infrastructure.in.exception_handling.handlers;

import com.denmiagkov.meter.application.service.exceptions.AuthenticationFailedException;
import com.denmiagkov.meter.infrastructure.in.exception_handling.exceptions.HasNoAdminStatusException;
import com.denmiagkov.meter.infrastructure.in.exception_handling.exceptions.UtilityTypeNotFoundException;
import com.denmiagkov.meter.infrastructure.in.exception_handling.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String EXCEPTION_MESSAGE = "EXCEPTION OCCURRED: ";

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleException(AuthenticationFailedException exception) {
        log.error(EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HasNoAdminStatusException.class)
    public ResponseEntity<String> handleException(HasNoAdminStatusException exception) {
        log.error(EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UtilityTypeNotFoundException.class)
    public ResponseEntity<String> handleException(UtilityTypeNotFoundException exception) {
        log.error(EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<String> handleException(RuntimeException exception) {
        log.error(EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
