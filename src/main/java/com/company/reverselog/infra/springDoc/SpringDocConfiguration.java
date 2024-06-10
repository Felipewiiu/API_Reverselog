package com.company.reverselog.infra.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    String desc = "API Reverselog é um sistema de gerenciamento " +
            "de devoluções desenvolvido para automatizar o processo" +
            " de devolução de produtos em uma empresa de varejo";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("ReverseLog API")
                        .description(desc)
                        .contact(new Contact()
                                .name("Time backend")
                                .email("engenharia2Flex@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                        )
                );
    }
}
