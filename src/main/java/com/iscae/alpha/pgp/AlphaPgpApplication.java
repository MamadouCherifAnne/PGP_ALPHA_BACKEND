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
		/*	RoleRepository roleRepo=ctx.getBean(RoleRepository.class);
			UtilisateurRepository userRepo=ctx.getBean(UtilisateurRepository.class);
			
			Role r2=roleRepo.save(new Role("USER", null));
			userRepo.save(new Utilisateur("user2020", "baba", "mmd@pg", "12345", "nktt", true, "45", r2, null, null, null, null, null));
		}*/
		
		/*ProjetRepository projetRepository = ctx.getBean(ProjetRepository.class);
		
		Projet projet = new Projet();
		projet.setNumProjet(1L);
		projet.setDebutProjet(null);
		projet.setFinProjet(null);
		projet.setNomProjet("first projet");
		projet.setPhases(null);
		projet.setResponsables(null);
		projet.setRisques(null);
		projet.setZoneRealisation("Bababé");
		projetRepository.save(projet);*/
		
	}

	}

