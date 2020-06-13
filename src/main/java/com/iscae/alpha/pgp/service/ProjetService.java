package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import com.iscae.alpha.pgp.entities.Projet;

public interface ProjetService {
     
	Projet AddProjet(Projet projet);
	void updateProjet(Projet projet);
	void deleateProjet(Long projetId);
	Projet findProjetById(Long projetId);
	List<Projet> findAllProjets();
	Projet findByDateDebut(Date dateDebut);
	Projet importer(Projet projet);
	Projet export(Projet projet);
}
