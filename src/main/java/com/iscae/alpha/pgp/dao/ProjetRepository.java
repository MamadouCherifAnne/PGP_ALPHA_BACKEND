package com.iscae.alpha.pgp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iscae.alpha.pgp.entities.Fichier;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

	Projet findByDebutProjet(Date date);

	Projet findByFinProjet(Date dateFin);
	
	// Get tasks pour un projet
	
	@Query("Select task FROM Tache task, Projet p WHERE p.numProjet=?1 AND task.phase.projet.numProjet = p.numProjet AND task.type  NOT LIKE 'Jalon'")
	public List<Tache> projectTasks(Long numProjet);
	
	// Get all Jalon for this project 
	@Query("Select task FROM Tache task, Projet p WHERE p.numProjet=?1 AND task.phase.projet.numProjet = p.numProjet AND task.type='Jalon'")
	public List<Tache> allProjectJalons(Long numProjet);
	
	
	// Tout les utilistaeur
}
