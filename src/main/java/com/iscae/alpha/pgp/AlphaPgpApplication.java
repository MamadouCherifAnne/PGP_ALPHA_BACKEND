package com.iscae.alpha.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Role;
import com.iscae.alpha.pgp.entities.Utilisateur;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UtilisateurRepository.class)
public class AlphaPgpApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(AlphaPgpApplication.class, args);
		/*	RoleRepository roleRepo=ctx.getBean(RoleRepository.class);
			UtilisateurRepository userRepo=ctx.getBean(UtilisateurRepository.class);
			
			Role r2=roleRepo.save(new Role("USER", null));
			userRepo.save(new Utilisateur("user2020", "baba", "mmd@pg", "12345", "nktt", true, "45", r2, null, null, null, null, null));
		}*/
		
	}
	}

