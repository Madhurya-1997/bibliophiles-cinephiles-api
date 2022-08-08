package com.heritage.bibandcineapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info =
@Info(title = "BibAndCine API",
		version = "/api/v1.0",
		description = "Documentation for BibAndCine API")
)
public class BibAndCineApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibAndCineApiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


