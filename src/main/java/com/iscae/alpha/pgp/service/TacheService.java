package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.iscae.alpha.pgp.dto.InfoTaches;
import com.iscae.alpha.pgp.dto.MonTravail;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Depense;
import com.iscae.alpha.pgp.entities.Facture;
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
	
	// Les taches succeptibles de suivres une tache
	public List<Tache> potentielPredecesseurs(Long idTache);
	
	// Ajouter des commentaires a une tache
	public Tache addCommentToTask(List<Commentaire> comments);
	
	// Afficher la Liste des depenses d'une tache
	public List<Depense> getDepensesOfTask(Long idTache);
	
	// Afficher la facture d'une Tache
	public Facture getFactureOfTasK(Long idTache);
	
	// Cacul du cout  selon le temps reel effectuer des ressources humaines pour une tache
	public double calculCoutRessourcesOfTask(Long idTache);
	
	// Cacul du cout Provisoires selon le temps normale a passer des ressources humaines pour une tache
	public double calculEstimationCoutTache(Long idTache);
	
//  Le cout totales des depenses sur une tache
		public double getCoutTotaleDepense(Long idTache);

		//  Get The OWner of the task
		public String getTheOwner(Long idTache);
<<<<<<< HEAD
	
	// nbr de Taches EnCours
	public	int nbrTachesEnCours( Long projetId);
	
	// nbr de Taches Terminees
	public int nbrTachesTerminees( Long projetId);
	
	// nbr de taches en retard
	public int nbrTachesEnRetard( Long projetId);
	
	public InfoTaches TasksInformation(Long ProjetId);
	
	
=======
		
		// Has acces to the task
		public Long getProject(Long idTache);
		

>>>>>>> 38a87a1ffcca6a0db3c9eb3866a8e7ae7634dc90
}

