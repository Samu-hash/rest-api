package com.ternova.restapi.restapi.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternova.restapi.restapi.context.MetadataContext;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.models.response.RestApiResponse;
import com.ternova.restapi.restapi.models.response.RestApiResponseDataUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(UserAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        logger.info("-------- Configurando mensajes de excepción en provider");

        // Registro de información sobre la configuración de mensajes de excepción en el proveedor.

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Establece el código de estado de respuesta en "No autorizado".
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE); // Establece el tipo de contenido de la respuesta como JSON.

        String errorMessage = "Acceso no autorizado"; // Mensaje predeterminado si no hay información adicional.
        String exceptionType = "Unknown"; // Tipo de excepción predeterminado si no hay información adicional.

        if (authException != null) {
            errorMessage = authException.getMessage(); // Obtén el mensaje de error de la excepción.
            exceptionType = authException.getClass().getSimpleName(); // Obtén el tipo de excepción.
        }

        OBJ_MAPPER.writeValue(response.getOutputStream(),
                new RestApiResponse<RestApiResponseDataUser>(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        MetadataContext.getContextMetadata(),
                        null, new Error(HttpStatus.UNAUTHORIZED.value()+"", errorMessage, exceptionType) // Agrega detalles adicionales.
                )
        );
    }
}
