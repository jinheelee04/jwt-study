package com.jwt.study.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    NOT_FOUNT_USER("1000", "Not Found User"),
    INVALID_INPUT_VALUE("2000", "Invalid Input Value"),
    INVALID_EXPECTED_TYPE("2001", "Invalid Expected Type"),
    METHOD_NOT_ALLOWED("3000", "Method Not Allowed"),
    NOT_ALLOW_ACCESS("4000", "Access is Denied"),
    INVALID_GRANT("4000", "Invalid Grant"),
    DATABASE_INVALID_WORKING("5000", "Database process error"),
    CUSTOM_EXCEPTION_SAMPLE("CODE", "Custom Exception Sample"),
    ;

    private final String code;
    private final String message;

    ExceptionCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
