package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.Entreprise;

public interface EntrepriseService {
	
	Entreprise addEntreprise(Entreprise entreprise);
	Entreprise updateEntreprise(Entreprise entreprise);
	void deleateEntreprise(Long idEntreprise);
	Entreprise findEntrepriseById(Long idEntreprise);
	List<Entreprise>findAllEntreprise();
}
