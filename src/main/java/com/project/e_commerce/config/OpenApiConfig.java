package com.project.e_commerce.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "E-Commerce API",
        description = "Backend APIs for E-commerce project",
        version = "1.0",
        contact = @Contact(name = "Ye Yint Bo",email = "yeyintbo91@gmail.com")
    ),
    security = {
        @SecurityRequirement(name = "bearerAuth")
    }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "input JWT Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    
}
