package com.transaction.producer.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebComponentConfig {

	private RestTemplateBuilder builder;

	public WebComponentConfig(RestTemplateBuilder builder) {
		super();
		this.builder = builder;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return builder.setConnectTimeout(Duration.ofMillis(5000)).setReadTimeout(Duration.ofMillis(5000)).build();
	}
}
