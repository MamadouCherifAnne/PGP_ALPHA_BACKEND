package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;

public interface AffectationUtilisateurService {
	
	// Affecter un utilisateur a une tache
	public AffectationUtilisateur addAffectationUser(AffectationUtilisateur userJob);
	
	//Modifier Une Affectation
	public AffectationUtilisateur updateAffectationUser( AffectationUtilisateur userJob);
	
	//Suppression d'une affectation
	public boolean deleteAffectation(Long idUser,Long idTache);
	
	// Afficher La liste des Affectations concernant une tache
	public List<AffectationUtilisateur> getAffectationsForTache(Long idTache);
	
	// Afficher La liste des Affectations concernant un utlisateur
		public List<AffectationUtilisateur> getAffectationsForUser(Long idUser);
		
	
	
	

}
