package com.ternova.restapi.restapi.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;
    private final String message;
    private final Error error;

}
