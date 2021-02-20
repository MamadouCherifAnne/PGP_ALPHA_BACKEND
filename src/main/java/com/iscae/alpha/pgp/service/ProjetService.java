package com.iscae.alpha.pgp.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.iscae.alpha.pgp.dto.JalonDto;
import com.iscae.alpha.pgp.dto.MembreProjetDto;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.ProjectUserID;
import com.iscae.alpha.pgp.entities.ProjectUtilisateurs;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;

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

	// Liste des taches d'un projet

	public List<Tache> projectTasks(Long numProjet);
	// Afficher tout les jalons
		public List<Tache> getAllJalons(Long numProjet);
		
	// Afficher les jalons en retard
		public List<Tache> getRetardJalon(Long numProjet);
		
	// Ajouter des commentaires au projet
		public Projet addcommentsToProject(List<Commentaire> comments);
	
	// Afficher toutes les commentaires sur un projet
		public List<Commentaire> allCommentsOfProject(Long idProjet);
		
		// Add membre dans un projet
		public boolean addMembreToProject(ProjectUtilisateurs membreProjet);
		
		// Get The membre of project 
		public Collection<MembreProjetDto> getMembreOfProject(Long id);
		
		// Verfier le role d'un membre dans un projet
		public int verifRoleMembre(Long idProject, Long idUser);
		
		// Deleting a membre from project
		public boolean deleteMembreOfProject(ProjectUserID idMembre);
		
		// Verifier si c'est le chef de projet;
		public Utilisateur getProjectOwner(Long IdProject);
		

		// Afficher le cout  total d'un projet
		public double coutTotalProject(Long idProject);
		
		// Afficher le cout total d'un projet dans les 6 derniers mois;
		public double coutProjectLastMonth(Long idProject);

		public int getMyProjectsActifs(String username);
		
		public int getMyProjectsEnretards(String username);
		
		public int getMyProjectsTermines(String username);
		
		//envoi de rapport 
		// affichage de la liste des jalons en retards
		public JalonDto getJalonsInfos(Long numProjet);
		
		//etats des projets pour les admins
		public int getMyProjectsActifsAdmin();
		
		public int getMyProjectsEnretardsAdmin();
		
		public int getMyProjectsTerminesAdmin();
}
