package com.ternova.restapi.restapi.service;

import com.ternova.restapi.restapi.context.DataContext;
import com.ternova.restapi.restapi.context.MetadataContext;
import com.ternova.restapi.restapi.controller.components.ConnectToDataSource;
import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.models.request.RestApiRequestData;
import com.ternova.restapi.restapi.models.response.RestApiResponseDataUser;
import com.ternova.restapi.restapi.service.database.entity.Usuarios;
import com.ternova.restapi.restapi.service.database.repository.ParametersRepository;
import com.ternova.restapi.restapi.service.database.repository.UsuariosRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Objects;

@Service
public class UserValidateService {

    @Autowired
    private ParametersRepository parametersRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ConnectToDataSource connectToDataSource;

    public RestApiResponseDataUser returnUser(RestApiRequestData restApiRequestData){

        EntityManager em = connectToDataSource.connectToDataSource(MetadataContext.getContextMetadata().getDatabase());

        Integer i = Integer.valueOf(restApiRequestData.getIdUsuario());
        Usuarios u = em.find(Usuarios.class, i);
        //Usuarios u = usuariosRepository.findUser(i);

        if(!Objects.isNull(u)){
            RestApiResponseDataUser data = new RestApiResponseDataUser();
            data.setIdUsuario(u.getIdUsuario());
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
