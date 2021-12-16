package com.ghailene.Springbootwebfluxdemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info=@Info(
        title ="Springweb flux functional endpoint .",
        version ="1.0",
        description = "swagger documentation using open api."
))
public class SwaggerConfig {



}
