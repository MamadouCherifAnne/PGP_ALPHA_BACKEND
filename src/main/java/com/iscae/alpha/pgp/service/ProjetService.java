package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;

public interface ProjetService {

    //Ajouter un projett
	Projet AddProjet(Projet projet);
	 //modifier un projet
	boolean updateProjet(Projet projet);
	//supprimer un projet
	void deleateProjet(Long projetId);
	//rechercher un projet par id
	Projet findProjetById(Long projetId);
	//recuperer tous les projets
	List<Projet> findAllProjets();
	//rechercher par date de debut d'un projet 
	Projet findByDateDebut(Date dateDebut);
	//rechercher par date de fin d'un projet 
	Projet findByDateFin(Date dateFin);
	//importer un projet 
	Projet importer(Projet projet);
	//exporter un projet 
	Projet export(Projet projet);
	//tous les phase d'un projet 
	List<Phase> listPhaseProjet(Long idProjet);
	//tous les taches d'une phase d'un projet
	List<Tache> listTache(Long idProjet);
}
