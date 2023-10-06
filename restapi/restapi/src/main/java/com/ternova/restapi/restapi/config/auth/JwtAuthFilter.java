package com.ternova.restapi.restapi.config.auth;

import com.ternova.restapi.restapi.utils.CommonsConstant;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.exception.models.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    public JwtAuthFilter(UserAuthProvider userAuthProvider){
        this.userAuthProvider =userAuthProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.info("----Entro a dofilterInternal "); // Registro de entrada al método doFilterInternal.
        String header = request.getHeader(HttpHeaders.AUTHORIZATION); // Obtiene el encabezado "Authorization" de la solicitud.

        if (!Objects.isNull(header)) { // Comprueba si el encabezado no es nulo.
            String[] split = header.split(" "); // Divide el encabezado en sus partes.

            if (split.length == 2 && CommonsConstant.APPLICATION_AUTH_NAME.equals(split[0])) {
                // Verifica si el encabezado tiene dos partes y la primera parte coincide con APPLICATION_AUTH_NAME.

                logger.info("----validando split {}", split); // Registro de información sobre la validación del encabezado.

                try {
                    logger.info("----intenta validar el token"); // Registro de intento de validación del token.
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthProvider.validateToken(split[1]) // Valida el token y configura la autenticación en el contexto de seguridad.
                    );
                } catch (RuntimeException e) {
                    logger.info("----fallo en validacion de token {}", e.getMessage()); // Registro de un fallo en la validación del token.
                    SecurityContextHolder.clearContext(); // Limpia el contexto de seguridad.
                    throw new UnauthorizedException(new Error(HttpStatus.UNAUTHORIZED, e.getMessage())); // Lanza una excepción de no autorizado con el mensaje de error.
                }
            }
        }
        filterChain.doFilter(request, response); // Continúa con la cadena de filtros.
    }
}
