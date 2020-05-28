package com.iscae.alpha.pgp.service;

import java.util.List;

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
}
