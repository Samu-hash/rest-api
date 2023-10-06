package com.ternova.restapi.restapi.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RestApiRequestData {

    @NotNull(message = "001")
    @NotBlank(message = "002")
    @JsonProperty("id_usuario")
    private String idUsuario;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apellidos")
    private String apellidos;

    private Date fechaNacimiento;

    @JsonProperty("estado")
    private String estado;
}
