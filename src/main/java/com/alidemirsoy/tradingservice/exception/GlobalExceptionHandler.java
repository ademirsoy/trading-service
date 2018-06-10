package com.alidemirsoy.tradingservice.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequestException(BadRequestException ex) {
        log.warn("Request is not validated, " + ex.getMessage(), ex);
        return new ErrorResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRequestValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("Request is not validated: " + message, ex);
        return new ErrorResponse("request.validation.failed", message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRequestNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("Request format is not valid: " + ex.getMessage(), ex);
        return new ErrorResponse("request.validation.failed", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ErrorResponse handleInvalidCredentials(BadCredentialsException ex) {
        log.warn("Invalid credentials!");
        return new ErrorResponse("invalid.credentials", ex.getMessage());
    }

    @Data
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }
}
