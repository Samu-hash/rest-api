package com.ternova.restapi.restapi.utils;

import com.ternova.restapi.restapi.context.DataContext;
import com.ternova.restapi.restapi.context.MetadataContext;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.models.response.RestApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Authorizations {

    public ResponseEntity<RestApiResponse> getResponseSuccess(Object data){
        HttpStatus http = HttpStatus.OK;
        ResponseEntity<RestApiResponse> entity = new ResponseEntity<>(new RestApiResponse(
                http.value(), CommonsConstant.APP_CONNECTION_SUCCESS,
                MetadataContext.getContextMetadata(), data, null
        ), http);

        MetadataContext.clear();
        DataContext.clear();

        return entity;
    }

    public ResponseEntity<RestApiResponse> getResponseBadRequest(Error error){
        HttpStatus http = HttpStatus.BAD_REQUEST;
        ResponseEntity<RestApiResponse> entity = new ResponseEntity<>(new RestApiResponse(
                http.value(), CommonsConstant.APP_CONNECTION_SUCCESS,
                MetadataContext.getContextMetadata(), null, error
        ), http);

        MetadataContext.clear();
        DataContext.clear();

        return entity;
    }

    public ResponseEntity<RestApiResponse> getResponseInternal(Error error){
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseEntity<RestApiResponse> entity =  new ResponseEntity<>(new RestApiResponse(
                http.value(), CommonsConstant.APP_CONNECTION_SUCCESS,
                MetadataContext.getContextMetadata(), null, error
        ), http);

        MetadataContext.clear();
        DataContext.clear();

        return entity;
    }

    public ResponseEntity<RestApiResponse> getResponseUnauthorized(String message){
        HttpStatus http = HttpStatus.UNAUTHORIZED;
        ResponseEntity<RestApiResponse> entity =  new ResponseEntity<>(new RestApiResponse(
                http.value(), message,
                null, null, null
        ), http);

        MetadataContext.clear();
        DataContext.clear();

        return entity;
    }

}
