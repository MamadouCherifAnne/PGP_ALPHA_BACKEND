package com.iscae.alpha.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.iscae.alpha.pgp.dao.ProjetRepository;


@SpringBootApplication

public class AlphaPgpApplication {

	public static void main(String[] args) {


		SpringApplication.run(AlphaPgpApplication.class, args);

		
	}

	}

