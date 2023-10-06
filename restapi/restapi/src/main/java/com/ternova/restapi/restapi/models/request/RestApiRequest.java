package com.ternova.restapi.restapi.models.request;

import com.ternova.restapi.restapi.models.Metadata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApiRequest {

    @Valid
    @NotNull(message = "001")
    private Metadata metadata;

    @Valid
    @NotNull(message = "001")
    private RestApiRequestData data;
}
