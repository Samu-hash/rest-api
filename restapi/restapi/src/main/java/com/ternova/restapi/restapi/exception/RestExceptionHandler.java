package com.ternova.restapi.restapi.exception;

import com.ternova.restapi.restapi.utils.Authorizations;
import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.exception.models.UnauthorizedException;
import com.ternova.restapi.restapi.models.response.RestApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    private Authorizations authResponse;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<RestApiResponse> handleException(BusinessException bex){
        logger.info("--- handleException, {}", bex);
        return authResponse.getResponseBadRequest(bex.getError());
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RestApiResponse> handleExceptionUnauth(UnauthorizedException bex){
        logger.info("--- handleExceptionUnauth, {}", bex);
        return authResponse.getResponseUnauthorized(bex.getMessage());
    }
}
