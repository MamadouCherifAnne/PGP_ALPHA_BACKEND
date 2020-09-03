package com.iscae.alpha.pgp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.FactureRepository;
import com.iscae.alpha.pgp.entities.Facture;

@Service
public class FactureServiceImpl implements FactureService {
	@Autowired
	FactureRepository factureRepo;
	@Autowired
	TacheService tacheService;

	@Override
	public Facture addFactureOfTask(Facture facture) {
		// TODO Auto-generated method stub
		 
		
		double coutRessourceOfTache = tacheService.calculCoutRessourcesOfTask(facture.getTache().getNumTache());
		double coutTotaleDepense = tacheService.getCoutTotaleDepense(facture.getTache().getNumTache());
		double totale = coutRessourceOfTache+coutTotaleDepense;
		facture.setMontantFacture(totale);
		
		
		return factureRepo.save(facture);
	}

	@Override
	public boolean deleteFacture(Long idFacture) {
		// TODO Auto-generated method stub
		if(factureRepo.findById(idFacture).isPresent()) {
			factureRepo.deleteById(idFacture);
			
		}
		return false;
	}

}
