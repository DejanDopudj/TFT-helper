package com.ftn.sbnz.service.exception;

import com.ftn.sbnz.service.exception.error.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ExceptionResponseBody handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ExceptionResponseBody(
                HttpStatus.BAD_REQUEST.value(),
                "Username or email already exists.");
    }
}
