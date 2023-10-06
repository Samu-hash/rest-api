package com.ternova.restapi.restapi.exception.models;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UnauthorizedException extends RuntimeException {

    private Error error;

    public UnauthorizedException(Error error){
        super("");
        this.error = error;
    }
}
