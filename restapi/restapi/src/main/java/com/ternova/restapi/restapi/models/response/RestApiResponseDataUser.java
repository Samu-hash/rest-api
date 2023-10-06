package com.ternova.restapi.restapi.models.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RestApiResponseDataUser {

    private String idUsuario;

    private String nombres;

    private String apellidos;

    private Date fechaNacimiento;

    private String estado;
}
