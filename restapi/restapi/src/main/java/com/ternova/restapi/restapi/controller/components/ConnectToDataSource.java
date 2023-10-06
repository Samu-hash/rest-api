package com.ternova.restapi.restapi.controller.components;


import com.ternova.restapi.restapi.config.OpenApiConfig;
import com.ternova.restapi.restapi.config.database.DataSourceConfig;
import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.exception.models.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

@Service
public class ConnectToDataSource {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    private static final Logger logger = LoggerFactory.getLogger(ConnectToDataSource.class);

    public void connectToDataSource(String connect){
        DataSource selectDataSource =  dataSourceConfig.getDataSourceTernova();
        if(!Objects.isNull(selectDataSource)){
            try(Connection connection = selectDataSource.getConnection()) {

                logger.info("----------- se conecto a la base de datos {}", connect);
            }catch (Exception e){
                throw new BusinessException(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Error(HttpStatus.BAD_REQUEST, "Error al conectar hacia la db: ".concat(connect))
                );
            }
        }else
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    new Error(HttpStatus.BAD_REQUEST, "Base de datos desconocida: ".concat(connect))
            );
    }
}
