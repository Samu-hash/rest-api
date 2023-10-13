package com.ternova.restapi.restapi.service.database;

import com.ternova.restapi.restapi.exception.models.BusinessException;
import com.ternova.restapi.restapi.exception.models.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Method;

@Service
public class ConnectToDataSource {

    private final Logger logger = LoggerFactory.getLogger(ConnectToDataSource.class);

    @Autowired
    @Qualifier("getDataSourceTernova1")
    private DataSource getDataSourceTernova1;

    @Autowired
    @Qualifier("getDataSourceTernova2")
    private DataSource getDataSourceTernova2;

    @Autowired
    private ConfigurableEnvironment environment;


    public Integer connectToDataSource(String databaseName){
        logger.info("base de datos a conectar {}", databaseName);
        return this.activeProfile(databaseName);
    }

    private Integer activeProfile(String databaseName){
        return switch (databaseName) {
            case "ternova1" -> {
                environment.setActiveProfiles("ternova1");
                yield 1;
            }
            case "ternova2" -> {
                environment.setActiveProfiles("ternova2");
                yield 2;
            }
            default -> throw new BusinessException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    new Error(
                            HttpStatus.BAD_REQUEST.value() + "",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Base de datos no reconocida"));
        };
    }

    public Object requestClass(String classFind, String numberDb) {
        try{
            Class<?> clazz = Class.forName("com.ternova.restapi.restapi.service.database.repository.db"
                    .concat(numberDb).concat(".")
                    .concat(classFind).concat("Db".concat(numberDb)));

            return new DynamicInterfaceInvoker<>(clazz).getTarget();
            
        }catch (Exception e){
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    new Error(
                            HttpStatus.BAD_REQUEST.value() + "",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No se puede instanciar la clase"));
        }
    }

    public <T> Object invokeDynamicMethod(T dynamicInterface, String methodName, Object... arguments) {
        try {
            // Obtén la clase de la interfaz dinámica
            Class<?> interfaceClass = dynamicInterface.getClass();

            // Busca el método con el nombre especificado
            Method method = interfaceClass.getMethod(methodName, getParameterTypes(arguments));

            // Invoca el método en el objeto de la interfaz con los argumentos proporcionados
            return method.invoke(dynamicInterface, arguments);
        } catch (Exception e) {
            // Manejar excepciones, como NoSuchMethodException, IllegalAccessException, InvocationTargetException, etc.
            e.printStackTrace();
            return null; // O manejar el error de alguna otra manera
        }
    }

    // Método auxiliar para obtener los tipos de los parámetros
    private Class<?>[] getParameterTypes(Object[] arguments) {
        Class<?>[] parameterTypes = new Class[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            parameterTypes[i] = arguments[i].getClass();
        }
        return parameterTypes;
    }

}