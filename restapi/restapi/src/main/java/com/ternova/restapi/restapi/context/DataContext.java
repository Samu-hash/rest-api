package com.ternova.restapi.restapi.context;

import com.ternova.restapi.restapi.models.AuthModelToken;

import java.util.Objects;

public class DataContext {

    private static ThreadLocal<AuthModelToken> dataStringContext = new ThreadLocal<>();

    public static void setDataStringContext(AuthModelToken data){
        dataStringContext.set(data);
    }

    public static AuthModelToken getDataStringContext(){
        return Objects.isNull(dataStringContext.get()) ? createAuthNull() : dataStringContext.get();
    }

    public static AuthModelToken createAuthNull(){
        return new AuthModelToken("", "", "");
    }

    public static void clear(){
        dataStringContext.remove();
    }
}
