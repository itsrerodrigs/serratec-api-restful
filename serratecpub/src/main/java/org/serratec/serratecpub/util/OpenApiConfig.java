package org.serratec.serratecpub.util;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "API para gestão do Ecommerce de um PUB!",
		version = "1.0",
		description = "Documentação da API SerratecPub."
		))
public class OpenApiConfig {
	
	
}
