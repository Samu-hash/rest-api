package com.ternova.restapi.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthModelToken {

    private String username;
    private String password;
}
