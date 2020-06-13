package com.iscae.alpha.pgp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.entities.Profession;

@Service
public class ProfessionServiceImplementation implements ProfessionService {
	
	@Autowired
	ProfessionRepository profRepo;

	@Override
	public Profession addProfession(Profession profession) {
		
		return profRepo.save(profession);
		
	}

	@Override
	public Profession updateProfession(Profession profession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleateProfession(Long idProfession) {
		
		profRepo.deleteById(idProfession);
		
	}

	@Override
	public Profession findProfessionById(Long idProfession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profession> getAllProfession() {
		// TODO Auto-generated method stub
		return profRepo.findAll();
	}

}
