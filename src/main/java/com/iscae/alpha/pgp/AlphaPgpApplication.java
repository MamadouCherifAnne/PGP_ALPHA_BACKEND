package com.iscae.alpha.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication

public class AlphaPgpApplication extends SpringBootServletInitializer {
	
	// Creation du bean rest Template pour la consommation des serivces rest des autres api
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	

	public static void main(String[] args) {


		SpringApplication.run(AlphaPgpApplication.class, args);

		
	}

	}

