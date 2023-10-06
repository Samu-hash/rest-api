package com.ternova.restapi.restapi.config;

import com.ternova.restapi.restapi.config.auth.JwtAuthFilter;
import com.ternova.restapi.restapi.config.auth.UserAuthEntryPoint;
import com.ternova.restapi.restapi.config.auth.UserAuthFilter;
import com.ternova.restapi.restapi.config.auth.UserAuthProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private UserAuthEntryPoint userAuthEntryPoint;

    @Autowired
    private UserAuthProvider userAuthProvider;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    // Definición de un SecurityFilterChain personalizado que configura la seguridad en la aplicación.

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("------- ingreso a securityFilterChain."); // Registro de un mensaje informativo sobre el inicio de la configuración de seguridad.
        http
                .authorizeHttpRequests((request) ->
                        request.requestMatchers(HttpMethod.POST, this.getPatterns()).permitAll() // Permite todas las solicitudes POST que coincidan con los patrones especificados.
                                .anyRequest().authenticated()) // Requiere autenticación para cualquier otra solicitud.

                // Agrega un filtro personalizado (UserAuthFilter) antes del BasicAuthenticationFilter.
                .addFilterBefore(new UserAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)

                // Agrega un filtro personalizado (JwtAuthFilter) antes del UserAuthFilter.
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), UserAuthFilter.class)

                .csrf(AbstractHttpConfigurer::disable) // Deshabilita la protección CSRF.

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura la gestión de sesiones como sin estado.

                .exceptionHandling(exception -> exception.authenticationEntryPoint(userAuthEntryPoint)); // Configura el punto de entrada de autenticación personalizado.

        return http.build(); // Devuelve la configuración de seguridad construida.
    }

    private String[] getPatterns() {
        String[] patterns = {"/access-auth"};

        return patterns;
    }
}
