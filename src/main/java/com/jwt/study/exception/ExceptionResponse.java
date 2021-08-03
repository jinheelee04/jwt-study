package com.jwt.study.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ExceptionResponse {

    private String message;

    public ExceptionResponse(String message) {

        this.message = message;
    }
}
