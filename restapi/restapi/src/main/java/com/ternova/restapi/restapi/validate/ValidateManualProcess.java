package com.ternova.restapi.restapi.validate;

import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.exception.models.UnauthorizedException;
import com.ternova.restapi.restapi.models.AuthModelToken;
import com.ternova.restapi.restapi.utils.CommonsConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidateManualProcess {

    @Value("${security.jwt.token.username}")
    private String username;

    @Value("${security.jwt.token.password}")
    private String password;

    public AuthModelToken verifyCredentials(AuthModelToken authModelToken){

        if(Objects.isNull(authModelToken))
            throw new UnauthorizedException(new Error(HttpStatus.UNAUTHORIZED,
                    CommonsConstant.UNAUTHORIZED_CREDENTIALS));
        else if(username.equals(authModelToken.getUsername())
                && password.equals(authModelToken.getPassword()))
            return authModelToken;
        else
            throw new UnauthorizedException(new Error(HttpStatus.UNAUTHORIZED,
                    CommonsConstant.UNAUTHORIZED_CREDENTIALS));
    }
}
