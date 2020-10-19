package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.dto.Role;
import com.iscae.alpha.pgp.entities.Message;
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
		

	
}
