package com.ternova.restapi.restapi.utils;


import java.util.Arrays;
import java.util.List;

public class CommonsConstant {

    public static final String APPLICATION_NAME = "Usuarios";
    public static final String APPLICATION_NAME_VALIDATE = APPLICATION_NAME+" - Field error";

    public static final String APP_CONNECTION_FAILED = APPLICATION_NAME+" - Connection failed with %s";

    public static final String APP_CONNECTION_SUCCESS = APPLICATION_NAME + " - Transaccion success";

    public static final String APP_PATH_NO_AUTH = "/access-auth";

    public static final String UNAUTHORIZED_CREDENTIALS = "Credenciales incorrectas";

    public static final String APPLICATION_PIPE = "|";

    public static final String APPLICATION_AUTH_NAME = "Bearer";

    public static final String APPLICATION_AUTH_HEADER_USER = "username-auth";

    public static final String APPLICATION_AUTH_HEADER_PASS = "password-auth";

    public static final String APPLICATION_AUTH_HEADER_DB = "db-auth";

    public static final List<String> LIST_DB = Arrays.asList("ternova1", "ternova2", "ternova3", "ternova4", "ternova5");

}
