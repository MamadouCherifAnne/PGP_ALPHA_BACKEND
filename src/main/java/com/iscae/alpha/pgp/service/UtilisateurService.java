package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.dto.InfosMessageNonLu;
import com.iscae.alpha.pgp.dto.MonTravail;
import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Message;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;

public interface UtilisateurService {
	// Ajout d'un utilisateur
	public Utilisateur addUser(Utilisateur user);
	
	// Modification du profil de l'utilisateur
	public Utilisateur updateUser(Utilisateur user);
	
	//Suppression  de l'utilisateur
	public boolean deleteUser(Long id);
	
	// Afficher les Utilisateurs
	public List<Utilisateur> getAllUsers();
	
	// Rechercher Un utilisateur par son Nom
	public Utilisateur getUserByName(String nom);
	
	public Utilisateur getUserByUsername(String username);
	
	// Recherche par identifiant
	public Utilisateur getUserById(Long id);
	
	// Afficher la liste de toutes les tache dont il est affecter
	public List<Tache> TacheToRealise(Long idUser);
	
	// Afficher tout les message Envoye par un utilisateur
	public List<Message> getAllSendedMessageFromUser(Long idUser);
	
	// Afficher tout les message Reues par un utilisateur
		public List<Message> getAllRecivedMessageFromUser(Long idUser);
		
	// Afficher l' entreprise dont l'utilisateur est affecte
		public Entreprise getUserEntreprise(Long idUser);
		
	// Afficher les projets dont l'utilisateur est affecte a travers ces taches
		public List<Projet> getMyProjects(String username);
		
	//
	public List<MonTravail> tasksUser(Long idUser);

// Afficher tout les message Reues par un utilisateur
		public InfosMessageNonLu getMessageNonLu(String username);

		// Afficher les projets d'un utilisateur
		public List<Projet> getProjectOfUser(String username );
		
		// les projets en cours 
		public List<Projet> getProjetsEncours(String username);
		
		// les projets en retard
		public List<Projet> getProjetEnretard(String username);
		
		// les projets terminés
		public List<Projet> getProjetTermines(String username);
		
		
		// Les projets et ces etats pour un administrateurs
		
		// Afficher les projets d'un utilisateur admin
		//public List<Projet> getProjectOfAdmins( );
		
		// les projets en cours  admin
		public List<Projet> getProjetsEncoursAdmin();
		
		// les projets en retard
		public List<Projet> getProjetEnretardAdmin();
		
		// les projets terminés
		public List<Projet> getProjetTerminesAdmin();
}
