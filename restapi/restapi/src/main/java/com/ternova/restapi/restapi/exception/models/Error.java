package com.ternova.restapi.restapi.exception.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class Error {

    private String code;
    private String title;
    private String detail;

    public Error(){}

    public Error(String code, String title, String detail) {
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    public Error(HttpStatus status, String detail){
        this.code = status.value()+"";
        this.title = status.getReasonPhrase();
        this.detail = detail;
    }
}
