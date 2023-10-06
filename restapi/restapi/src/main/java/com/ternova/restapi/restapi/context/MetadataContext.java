package com.ternova.restapi.restapi.context;

import com.ternova.restapi.restapi.models.Metadata;

import java.util.Objects;

public class MetadataContext {

    private static final ThreadLocal<Metadata> contextMetadata = new ThreadLocal<>();

    public static Metadata getContextMetadata(){
        return contextMetadata.get();
    }

    public static void setContextMetadata(Metadata metadata){
        if(Objects.isNull(metadata))
            contextMetadata.set(new Metadata());
        else
            contextMetadata.set(metadata);
    }

    public static void clear(){
        contextMetadata.remove();
    }
}
