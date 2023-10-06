package com.ternova.restapi.restapi.config.auth;

import com.ternova.restapi.restapi.models.AuthModelToken;
import com.ternova.restapi.restapi.utils.CommonsConstant;
import com.ternova.restapi.restapi.context.DataContext;
import com.ternova.restapi.restapi.exception.models.Error;
import com.ternova.restapi.restapi.exception.models.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserAuthFilter extends OncePerRequestFilter{

    private final UserAuthProvider provider;

    private static final Logger logger = LoggerFactory.getLogger(UserAuthFilter.class);

    public UserAuthFilter(UserAuthProvider provider){
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.info("--------- ingresando al primer filtro, el punto de acceso");
        // Registro de entrada al primer filtro, que es un punto de acceso.

        if (CommonsConstant.APP_PATH_NO_AUTH.equals(request.getServletPath()) &&
                HttpMethod.POST.matches(request.getMethod())) {
            // Comprueba si la ruta de la solicitud es igual a la ruta de acceso sin autenticación y si el método de solicitud es POST.

            String username = request.getHeader(CommonsConstant.APPLICATION_AUTH_HEADER_USER);
            String password = request.getHeader(CommonsConstant.APPLICATION_AUTH_HEADER_PASS);
            String connect = request.getHeader(CommonsConstant.APPLICATION_AUTH_HEADER_DB);

            try {

                AuthModelToken authModelToken = new AuthModelToken(username, password, connect);

                logger.info("------ Intentando autenticar el usuario");
                // Registro de intento de autenticación de usuario.

                SecurityContextHolder.getContext().setAuthentication(
                        provider.validateCredentials(authModelToken)
                );
                // Autentica al usuario y configura la autenticación en el contexto de seguridad.

                DataContext.setDataStringContext(authModelToken);
                // Establece los datos del contexto con el nombre de usuario y contraseña.

            } catch (RuntimeException e) {
                logger.info("--------- no se pudo autenticar el usuario, e {}", e.getMessage());
                // Registro de fallo en la autenticación del usuario.

                SecurityContextHolder.clearContext();
                DataContext.clear();
                // Limpia el contexto de seguridad y los datos del contexto.

                throw new UnauthorizedException(new Error(HttpStatus.UNAUTHORIZED, "no se pudo autenticar el usuario "+e.getMessage()));
                // Lanza una excepción de no autorizado con el mensaje de error.
            }
        }

        filterChain.doFilter(request, response);
        // Continúa con la cadena de filtros.
    }
}
