package com.iscae.alpha.pgp.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;

public interface AffectationUtilisateurRepository extends JpaRepository<AffectationUtilisateur, UserToTache>{
 
	// Afficher les affectations par utilisateur
	
	@Query("Select a FROM AffectationUtilisateur a WHERE a.user_task.idUser = ?1")
	public List<AffectationUtilisateur> getAffectationsByUtilisateur(Long idUser);
	
	// Tousd les uitliateurs affecter a une tache
	
	@Query("Select a FROM AffectationUtilisateur a WHERE a.user_task.idTache = ?1")
	public List<AffectationUtilisateur> getAffectationsForTache(Long idTache);
	
	//
}
