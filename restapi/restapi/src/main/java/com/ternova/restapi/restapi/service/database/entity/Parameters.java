package com.ternova.restapi.restapi.service.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "parameters")
public class Parameters {

    @Id
    @Column(name = "id_param")
    private String idParam;

    @Column(name = "value_param")
    private String valueParam;

}
