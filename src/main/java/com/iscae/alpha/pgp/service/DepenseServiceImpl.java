package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.DepenseRepository;
import com.iscae.alpha.pgp.entities.Depense;

@Service
public class DepenseServiceImpl  implements DepenseService{
	@Autowired
	DepenseRepository depenseRepo;

	@Override
	public Depense addDepense(Depense depense) {
		// Ajout d'une depense
	
		return depenseRepo.save(depense);
	}

	@Override
	public boolean deleteDepense(Long idDepense) {
		// Suppression d'une depense
		Depense depense = findDepenseById(idDepense);
		if(depense != null) {
			depenseRepo.deleteById(idDepense);
			return true;
		}
		return false;
	}

	@Override
	public Depense findDepenseById(Long idDepense) {
		// TODO Auto-generated method stub
		if(depenseRepo.findById(idDepense).isPresent()) {
			
			return  depenseRepo.findById(idDepense).get();
		};
		return null;
	}

	@Override
	public List<Depense> getDepenseByDate(Date dateSauvegarde) {
		// Afficher les depenses selon leurs date de sauvegarde
		List<Depense> depenses = new ArrayList<>();
		return depenses ;
	}

}
