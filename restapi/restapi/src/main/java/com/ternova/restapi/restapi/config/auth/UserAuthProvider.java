package com.ternova.restapi.restapi.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.ternova.restapi.restapi.models.AuthModelToken;
import com.ternova.restapi.restapi.utils.CommonsConstant;
import com.ternova.restapi.restapi.context.DataContext;
import com.ternova.restapi.restapi.service.UserValidateService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.time}")
    private Integer intervalTime;

    private final UserValidateService userValidateService;

    private static final Logger logger = LoggerFactory.getLogger(UserAuthProvider.class);

    public UserAuthProvider(UserValidateService userValidateService){
        this.userValidateService = userValidateService;
    }

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken() {
        logger.info("-------parametrizando variables de token");
        // Registro de inicio de la parametrización de las variables del token.
        Date now = new Date();
        Date limit = new Date(now.getTime() + intervalTime); // Calcula la fecha de vencimiento del token.
        Algorithm alg = Algorithm.HMAC256(this.secretKey); // Configura el algoritmo de firma.

        AuthModelToken values = DataContext.getDataStringContext(); // Obtiene los valores del contexto.

        Gson gson = new Gson();

        DataContext.clear(); // Limpia el contexto de datos.
        logger.info("-------iniciando la creacion de token");
        // Registro de inicio de la creación del token.
        return JWT.create().withIssuer(gson.toJson(values)) // Crea el token con el emisor, fecha de emisión y fecha de vencimiento.
                .withIssuedAt(now).withExpiresAt(limit)
                .sign(alg); // Firma el token y lo devuelve.
    }

    public Authentication validateToken(String token) {
        logger.info("-------iniciando de la validacion de token");
        // Registro de inicio de la validación del token.
        Algorithm alg = Algorithm.HMAC256(secretKey); // Configura el algoritmo de verificación.
        JWTVerifier verifier = JWT.require(alg).build(); // Crea un verificador de token JWT.

        DecodedJWT decoded = verifier.verify(token); // Verifica el token y decodifica sus detalles.

        Gson gson = new Gson();
        AuthModelToken authModelToken = gson.fromJson(decoded.getIssuer(), AuthModelToken.class);

        String[] split = decoded.getIssuer().split("\\" + CommonsConstant.APPLICATION_PIPE); // Divide el emisor en partes.

        logger.info("-------iniciando verificacion de credenciales");
        // Registro de inicio de la verificación de credenciales.
        userValidateService.verifyCredentials(authModelToken); // Verifica las credenciales del usuario.

        DataContext.setDataStringContext(authModelToken);

        return new UsernamePasswordAuthenticationToken(decoded.getIssuer(), null, Collections.emptyList());
        // Crea y devuelve una autenticación basada en el emisor del token.
    }

    public Authentication validateCredentials(AuthModelToken authModelToken) {
        logger.info("-------validacion de credenciales");
        // Registro de inicio de la validación de credenciales.
        AuthModelToken values = userValidateService.verifyCredentials(authModelToken); // Verifica las credenciales del usuario.
        return new UsernamePasswordAuthenticationToken(values, null, Collections.emptyList());
        // Crea y devuelve una autenticación basada en las credenciales verificadas.
    }

}
