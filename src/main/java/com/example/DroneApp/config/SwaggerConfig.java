package com.example.DroneApp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Drone App")
                .version("1.0.0")
                 .description("Endpoints Documentation for Musala Soft Drone App Test"));

    }

}




