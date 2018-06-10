package com.alidemirsoy.tradingservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private final String code;

    public BadRequestException(String message, String code) {
        super(message);
        this.code = code;
    }
}
