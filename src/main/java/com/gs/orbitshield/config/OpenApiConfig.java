package com.gs.orbitshield.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.Components;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OrbitShield API")
                        .version("1.0.0")
                        .description("B2B REST API for satellite registration and collision risk monitoring."))
                .addSecurityItem(new SecurityRequirement().addList("ApiKeyAuth"))
                .components(new Components()
                        .addSecuritySchemes("ApiKeyAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("X-API-Key")
                                .description("API Key for authentication")));
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            Schema<?> errorResponseSchema = new Schema<>()
                    .type("object")
                    .addProperty("status", new Schema<>().type("string").example("error"))
                    .addProperty("error", new Schema<>().type("object")
                            .addProperty("code", new Schema<>().type("string").example("UNAUTHORIZED"))
                            .addProperty("message", new Schema<>().type("string").example("API Key is required."))
                            .addProperty("path", new Schema<>().type("string").example("/api/v1/resource")))
                    .addProperty("timestamp", new Schema<>().type("string").format("date-time").example("2026-05-31T18:00:00Z"));

            ApiResponse unauthorizedResponse = new ApiResponse()
                    .description("Unauthorized - API Key is missing or invalid")
                    .content(new Content()
                            .addMediaType("application/json", new MediaType()
                                    .schema(errorResponseSchema)));

            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses responses = operation.getResponses();
                if (!responses.containsKey("401")) {
                    responses.addApiResponse("401", unauthorizedResponse);
                }
            }));
        };
    }
}

