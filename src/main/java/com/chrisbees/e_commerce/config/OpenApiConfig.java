package com.chrisbees.e_commerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info (
        contact = @Contact (
            name = "Spring security JWT asymmetric Encryption demo",
            email = "contact@chris.com",
            url = "https://chrisbees.com"
        ),
        description = "OpenApi documentation from Spring Security Project",
        title = "OpenAPI Specification",
        version = "1.0",
        license = @License (
            name = "Licence name",
            url = "https://chrisbees.com/licence"
        ),
        termsOfService = "https://chrisbees.com/terms"
    ),
    servers = {
        @Server (
        url = "http://localhost:8081",
        description = "Local ENV"
    ),
    @Server (
        url = "https://prod.url",
        description = "Prod ENV"
    )
},
security = {
    @SecurityRequirement (
        name = "bearerAuth"
    )
}
)

@SecurityScheme (
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    
}
