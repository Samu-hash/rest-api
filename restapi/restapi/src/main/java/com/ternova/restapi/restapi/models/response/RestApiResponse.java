package com.ternova.restapi.restapi.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.models.Metadata;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RestApiResponse<T> {

    private Integer status;
    private String message;
    private Metadata metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Error error;
}
