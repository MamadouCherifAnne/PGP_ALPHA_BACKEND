package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Utilisateur;

public interface EntrepriseService {
	//Ajout d'une entreprise comme espace de travail principale
	Entreprise addEntreprise(Utilisateur proprietaire);
	
	//Modifier certains donnee de l'entreprise
	Entreprise updateEntreprise(Entreprise entreprise);
	//Suppression d'une entreprise
	void deleateEntreprise(Long idEntreprise);
	// Chercher l'entreprise par son identifiant
	Entreprise findEntrepriseById(Long idEntreprise);
	List<Entreprise>findAllEntreprise();
	
	// Ajout d'une sous entreprise dans l' espace de travail
	public Entreprise addSousEntreprise(Entreprise sousEntreprise);
	
}
