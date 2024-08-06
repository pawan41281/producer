package com.transaction.producer.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Autowired
	private EnvironmentConfig envConfig;

	@Bean
	OpenAPI defineOpenApi() {
		
		Server server = new Server();
		server.setUrl(envConfig.getEnv());
		server.setDescription(envConfig.getDesc());

		Contact myContact = new Contact();		
		myContact.setName(envConfig.getName());
		myContact.setEmail(envConfig.getEmail());
		
		
		Info information = new Info()
								.title("Transaction Producer Service")
								.version("1.0")
								.description("This API exposes for transaction procedure operation.")
								.contact(myContact);

		return new OpenAPI().info(information).servers(List.of(server));
	}

}
