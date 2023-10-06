package com.ternova.restapi.restapi.utils;

import java.util.Objects;

public class NumberUtility {

    public static boolean isNumeric(String value){
        return (!Objects.isNull(value) && value.matches("\\d+"));
    }
}
