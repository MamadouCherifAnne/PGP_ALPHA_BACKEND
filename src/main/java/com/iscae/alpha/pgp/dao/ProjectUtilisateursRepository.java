package com.iscae.alpha.pgp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.ProjectUserID;
import com.iscae.alpha.pgp.entities.ProjectUtilisateurs;

public interface ProjectUtilisateursRepository extends JpaRepository<ProjectUtilisateurs, ProjectUserID> {

	// Tousd les uitliateurs affecter a une tache
	
		@Query("Select a FROM ProjectUtilisateurs a WHERE a.idMembre.idProjet = ?1")
		public List<ProjectUtilisateurs> getProjectsMembres(Long idProjet);
		
		@Query("Select a FROM ProjectUtilisateurs a WHERE a.idMembre.idUser = ?1")
		public List<ProjectUtilisateurs> getUserProjects(Long idUser);
		
		
}
