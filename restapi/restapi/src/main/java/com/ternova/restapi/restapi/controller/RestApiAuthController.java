package com.ternova.restapi.restapi.controller;

import com.ternova.restapi.restapi.utils.Authorizations;
import com.ternova.restapi.restapi.config.auth.UserAuthProvider;
import com.ternova.restapi.restapi.context.MetadataContext;
import com.ternova.restapi.restapi.models.request.RestApiRequestAuth;
import com.ternova.restapi.restapi.models.response.RestApiResponseDataAuth;
import com.ternova.restapi.restapi.validate.ValidateModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RestApiAuthController {

    @Autowired
    private Authorizations authorizations;

    @Autowired
    private ValidateModels validateModels;

    @Autowired
    private UserAuthProvider provider;

    @PostMapping(value = "access-auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authentication(@AuthenticationPrincipal @RequestBody RestApiRequestAuth request) {

        MetadataContext.setContextMetadata(Objects.isNull(request) ? null : request.getMetadata());

        validateModels.validateModel(request);

        RestApiResponseDataAuth data = new RestApiResponseDataAuth();

        data.setToken(provider.createToken());

        return authorizations.getResponseSuccess(data);
    }

}
