package com.iscae.alpha.pgp.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.dto.AffectationsTacheDto;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;
import com.iscae.alpha.pgp.service.AffectationUtilisateurService;

@RestController
@RequestMapping("/affectation")
@CrossOrigin(origins = "*")

public class AffectationUtilisateurController {
	@Autowired
	AffectationUtilisateurService userToJobService;
	@PostMapping(value="/add", consumes="application/json")
	public boolean addAffectationUser(@RequestBody AffectationUtilisateur userForJob) {
		
			AffectationUtilisateur affect =userToJobService.addAffectationUser(userForJob);
		 System.out.println("#######+"+ userForJob.getUser_task().getIdUser());
		 if(affect!=null) {
		 return true;
		 }else {
			 return false;
		 }
	}
	
	// Modification d'une Affectation
	@PostMapping(value="/update", consumes="application/json")
	public AffectationUtilisateur updateAffectation(@RequestBody AffectationUtilisateur userForJob) {
		
		 return userToJobService.updateAffectationUser(userForJob);
		
	}
	
	
	// Service de Suppression d'Affectation 
	@PostMapping(value="/deleteAffectation",consumes = {"application/json"})
	public boolean deleteAffectation(@RequestBody UserToTache idAffect) {
		System.out.println("La Suppression dune affectatin ///////////////////**+++++++++++++++++");
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
		
		// Service de recuperation des affectations concernant une Tache avec appropriation du format avec le client
				@GetMapping(value="/tacheAffectationsFormater/{idTache}")
				public Collection<AffectationsTacheDto> getAllTacheAffectationsFormater(@PathVariable Long idTache){
					
					return userToJobService.getAffectationsForTacheFormater(idTache);
					
				}
			
			// Service de recuperation des affectations concernant une Tache avec appropriation du format avec le client
			@GetMapping(value="/LatestTacheToRealise/{username}")
			public Collection<AffectationsTacheDto> getAllLatestUserAffectations(@PathVariable String username){
				
				return userToJobService.getLatestAffectationsForUser(username);
				
			}

}
