package com.ternova.restapi.restapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Metadata {

    @NotNull(message = "001")
    @NotBlank(message = "002")
    @JsonProperty("user")
    private String user;

    @NotNull(message = "001")
    @NotBlank(message = "002")
    @JsonProperty("location")
    private String location;

    @NotNull(message = "001")
    @NotBlank(message = "002")
    @JsonProperty("application_id")
    private String applicationId;

    @NotNull(message = "001")
    @NotBlank(message = "002")
    @JsonProperty("service_id")
    private String serviceId;

    @NotNull(message = "001")
    @NotBlank(message = "002")
    @JsonProperty("date_time")
    private String dateTime;

}
