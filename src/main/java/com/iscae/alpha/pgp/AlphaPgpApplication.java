package com.iscae.alpha.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Role;
import com.iscae.alpha.pgp.entities.Utilisateur;


@SpringBootApplication

public class AlphaPgpApplication {

	public static void main(String[] args) {


		ApplicationContext ctx= SpringApplication.run(AlphaPgpApplication.class, args);
	

		ProjetRepository projetRepository = ctx.getBean(ProjetRepository.class);

		
	}

	}

