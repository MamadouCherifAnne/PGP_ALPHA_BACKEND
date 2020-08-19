package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;

public interface TacheService {
	
	Tache addTache(Tache tache);


	Tache updateTache( Tache tache);

	void deleateTache(Long idTache);
	Tache findTache(Long idTache);
	Tache findTahceByDebut(Date dateDebut);
	Tache findTacheByFin(Date dateFin);
	List<Tache> findAllTache();
	//liste des ressources affecter a la tache
	public List<Utilisateur> getAllRessources(Long idTache);
	
	// La liste des predecesseur
	public List<Tache> getPredecesseursTask(Long idTache);
	//Ajouter jalon
	
	public Tache addJalon(Tache tache);
	
	
}
