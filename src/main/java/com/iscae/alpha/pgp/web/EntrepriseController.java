package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.EntrepriseService;

@RestController
@RequestMapping("/entreprise")

@CrossOrigin(origins = "http://localhost:4200")

public class EntrepriseController {

	@Autowired
	EntrepriseService entrepriseService;
	
	// Ajout d'une espace de travail pour gerer les projets
	@PostMapping(value="/addEspaceTravail")
	public Entreprise addEspaceDeTravail(@RequestBody Utilisateur user){
		
		return entrepriseService.addEntreprise(user);
	}
}
