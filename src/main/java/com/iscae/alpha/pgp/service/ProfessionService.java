package com.iscae.alpha.pgp.service;


import java.util.List;

import com.iscae.alpha.pgp.entities.Profession;

public interface ProfessionService {
	//Ajout profession
	Profession addProfession(Profession profession);
	
	//Update Profession
	Profession updateProfession(Profession profession);
	
	//Suppression profession
	boolean deleteProfession(Long idProfession);
	
	//Recherche par id
	Profession findProfessionById(Long idProfession);
	
	//Afficher toutes les professions
	public List<Profession> getAllProfession();

}
