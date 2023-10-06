package com.ternova.restapi.restapi.controller;

import com.ternova.restapi.restapi.models.response.RestApiResponseDataUser;
import com.ternova.restapi.restapi.utils.Authorizations;
import com.ternova.restapi.restapi.context.MetadataContext;
import com.ternova.restapi.restapi.models.request.RestApiRequest;
import com.ternova.restapi.restapi.models.response.RestApiResponse;
import com.ternova.restapi.restapi.service.UserValidateService;
import com.ternova.restapi.restapi.validate.ValidateModels;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name="Api de usuarios", description = "Servicio de creacion de usuarios")
@RestController
public class RestApiController {

    @Autowired
    private ValidateModels validateModels;

    @Autowired
    private UserValidateService userValidateService;

    @Autowired
    private Authorizations authorizations;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RestApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RestApiResponse.class))})
    })
    @PostMapping(value = "find-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponse> procesarApi(@RequestBody RestApiRequest request){

        MetadataContext.setContextMetadata(Objects.isNull(request) ? null : request.getMetadata());

        validateModels.validateModel(request);

        RestApiResponseDataUser data = userValidateService.returnUser(request.getData());

        return authorizations.getResponseSuccess(data);
    }


}
