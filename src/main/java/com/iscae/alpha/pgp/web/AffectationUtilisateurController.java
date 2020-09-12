package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.UserToTache;
import com.iscae.alpha.pgp.service.AffectationUtilisateurService;

@RestController
@RequestMapping("/affectation")
@CrossOrigin(origins = "http://localhost:4200")

public class AffectationUtilisateurController {
	@Autowired
	AffectationUtilisateurService userToJobService;
	@PostMapping(value="/add", consumes="application/json")
	public String addAffectationUser(@RequestBody AffectationUtilisateur userForJob) {
		
			userToJobService.addAffectationUser(userForJob);
		 System.out.println("#######+"+ userForJob.getUser_task().getIdUser());
		 return "Added";
	}
	
	// Modification d'une Affectation
	@PostMapping(value="/update", consumes="application/json")
	public AffectationUtilisateur updateAffectation(@RequestBody AffectationUtilisateur userForJob) {
		
		 return userToJobService.updateAffectationUser(userForJob);
		
	}
	
	
	// Service de Suppression d'Affectation 
	@DeleteMapping(value="/deleteAffectation")
	public boolean deleteAffectation(@RequestBody UserToTache idAffect) {
		
		return userToJobService.deleteAffectation(idAffect.getIdUser(), idAffect.getIdTache());
	}
	
	
	// Afficher une Affectation par son Idnetifiant
	@GetMapping(value ="/getAffectationById/{idUser}/{idTache}")
	public AffectationUtilisateur getAffectationById(@PathVariable(name="idUser") Long idUser, @PathVariable(name="idTache") Long idTache) {
		return userToJobService.getAffectationById(idTache, idUser);
	}
		
	// Service de recuperation des affectations concernant un utilisateur
	@GetMapping(value="/tacheToRealise/{idUser}")
	public List<AffectationUtilisateur> getAllUserAffectations(@PathVariable Long idUser){
		
		return userToJobService.getAffectationsForUser(idUser);
		
	}
	
	// Service de recuperation des affectations concernant une Tache
		@GetMapping(value="/tacheAffectations/{idTache}")
		public List<AffectationUtilisateur> getAllTacheAffectations(@PathVariable Long idTache){
			
			return userToJobService.getAffectationsForTache(idTache);
			
		}

}
