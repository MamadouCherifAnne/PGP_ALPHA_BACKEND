package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Projet;

public interface EntrepriseService {
	//Ajout d'une entreprise comme espace de travail principale
	Entreprise addEntreprise(Entreprise proprietaire);
	
	//Modifier certains donnee de l'entreprise
	Entreprise updateEntreprise(Entreprise entreprise);
	//Suppression d'une entreprise
	public boolean deleteEntreprise(Long idEntreprise);
	// Chercher l'entreprise par son identifiant
	Entreprise findEntrepriseById(Long idEntreprise);
	List<Entreprise>findAllEntreprise();
	
	// Ajout d'une sous entreprise dans l' espace de travail
	//public Entreprise addSousEntreprise(Entreprise sousEntreprise);
	
	// Afficher les projets d'une entreprise
	public List<Projet> getEntrepriseProjects(Long idEntreprise);
	
	
}
