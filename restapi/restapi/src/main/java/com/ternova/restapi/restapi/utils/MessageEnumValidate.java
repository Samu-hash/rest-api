package com.ternova.restapi.restapi.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@AllArgsConstructor
@ToString
@Getter
public enum MessageEnumValidate {

    VALIDATE_NOT_NULL("001", CommonsConstant.APPLICATION_NAME_VALIDATE, "El campo %s es requerido"),
    VALIDATE_NOT_BLANK("002", CommonsConstant.APPLICATION_NAME_VALIDATE, "El campo %s no puede ir vacio"),
    VALIDATE_CODE_NOT_FOUND("000", CommonsConstant.APPLICATION_NAME_VALIDATE, "Codigo de error no encontrado");

    private final String code;

    private final String title;

    private final String detail;

    public static MessageEnumValidate findMessageByCode(String code){
        return Stream.of(MessageEnumValidate.values())
                .filter(enume -> enume.getCode().equals(code))
                .findAny().orElse(VALIDATE_CODE_NOT_FOUND);
    }

}
