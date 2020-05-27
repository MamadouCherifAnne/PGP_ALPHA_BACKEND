package com.iscae.alpha.pgp.service;

import com.iscae.alpha.pgp.entities.Profession;

public interface ProfessionService {
	
	Profession addProfession(Profession profession);
	Profession updateProfession(Profession profession);
	void deleateProfession(Long idProfession);
	Profession findProfessionById(Long idProfession);
}
