package com.ternova.restapi.restapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Value("${swagger.title}")
    private String swTitle;

    @Value("${swagger.description}")
    private String swDescription;

    @Value("${swagger.version}")
    private String swVersion;

    @Value("${swagger.license.name}")
    private String swLicName;

    @Value("${swagger.license.url}")
    private String swLicUrl;

    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfig.class);

    @Bean
    public OpenAPI customOpenApi() {
        logger.info("Configurando OpenAPI personalizado."); // Registro de configuración de OpenAPI personalizado.
        return new OpenAPI().info(this.apiInfo());
    }

    private Info apiInfo() {
        logger.info("Configurando información de la API."); // Registro de configuración de información de la API.
        return new Info()
                .title(this.swTitle)
                .description(this.swDescription)
                .version(this.swVersion)
                .license(this.apiLicense());
    }

    private License apiLicense() {
        logger.info("Configurando información de la licencia."); // Registro de configuración de información de la licencia.
        return new License().name(this.swLicName).url(this.swLicUrl);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        logger.info("Configurando la negociación de contenido por defecto."); // Registro de configuración de la negociación de contenido por defecto.
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
