package com.ternova.restapi.restapi.service;

import com.ternova.restapi.restapi.context.MetadataContext;
import com.ternova.restapi.restapi.service.database.ConnectToDataSource;
import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.models.request.RestApiRequestData;
import com.ternova.restapi.restapi.models.response.RestApiResponseDataUser;
import com.ternova.restapi.restapi.service.database.entity.Usuarios;
import com.ternova.restapi.restapi.service.database.repository.db1.UsuariosRepositoryDb1;
import com.ternova.restapi.restapi.service.database.repository.db2.UsuariosRepositoryDb2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserValidateService {

    @Autowired
    private UsuariosRepositoryDb1 ternova1;

    @Autowired
    private UsuariosRepositoryDb2 ternova2;

    @Autowired
    private ConnectToDataSource connectToDataSource;

    public RestApiResponseDataUser returnUser(RestApiRequestData restApiRequestData) {

        Integer i = connectToDataSource.connectToDataSource(
                MetadataContext.getContextMetadata().getDatabase());

        Usuarios u = null;
        switch (i){
            case 1:
                u = ternova1.findUser(Integer.valueOf(restApiRequestData.getIdUsuario()));
                break;
            case 2:
                u = ternova2.findUser(Integer.valueOf(restApiRequestData.getIdUsuario()));
                break;
        }


        if(!Objects.isNull(u)){
            RestApiResponseDataUser data = new RestApiResponseDataUser();
            data.setIdUsuario(String.valueOf(u.getIdUsuario()));
            data.setNombres(u.getNombres());
            data.setApellidos(u.getApellidos());
            data.setFechaNacimiento(u.getFechaNacimiento());
            data.setEstado(u.getEstado());
            return data;
        }else{
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    new Error(HttpStatus.BAD_REQUEST.value()+"",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(), "Usuario no encontrado."));
        }
    }

}
