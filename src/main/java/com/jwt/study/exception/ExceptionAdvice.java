package com.jwt.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("handleException", e);

        ExceptionResponse response = new ExceptionResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ExceptionResponse> handleInvalidParameterException(InvalidParameterException e,  HttpStatus status) {
        log.error("handleInvalidParameterException", e);

        ExceptionResponse response = new ExceptionResponse(e.getMessage());

        return new ResponseEntity<>(response, status);
    }

}
