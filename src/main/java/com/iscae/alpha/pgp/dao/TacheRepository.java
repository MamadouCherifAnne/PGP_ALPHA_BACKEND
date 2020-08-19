package com.iscae.alpha.pgp.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iscae.alpha.pgp.entities.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {

	
	Tache findTahceByDebutTache(Date dateDebut);
	Tache findTacheByfinTache(Date dateFin);
	
	// Afficher les potentiels taches predecesseur
	@Query("Select task From Tache task, Tache t, Projet proj WHERE proj.numProjet =?1 AND"
			+ " t.numTache =?2 AND task.numTache != t.numTache AND"
			+ " task.phase.projet.numProjet = proj.numProjet"
			+ " AND t.phase.projet.numProjet =  task.phase.projet.numProjet"
			+ "  AND task.debutTache < t.debutTache")
	public List<Tache> getPotentielPredecessor(Long idProjet, Long idTache);
	
	


}
