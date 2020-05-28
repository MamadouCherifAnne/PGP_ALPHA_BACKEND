package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.entities.Projet;

public class ProjetServiceImp implements ProjetService{
	
	@Autowired
	ProjetRepository projetRepository;
	
	@Override
	public Projet AddProjet(Projet projet) {
		return projetRepository.save(projet);
	}

	@Override
	public void updateProjet(Projet projet) {
		Optional<Projet> projet1 = projetRepository.findById(projet.getNumProjet());
		
		if(projet1 != null) {
			Projet projet2 = projet1.get();
			projet2.setNumProjet(projet.getNumProjet());
			projet2.setNomProjet(projet.getNomProjet());
			projet2.setDebutProjet(projet.getDebutProjet());
			projet2.setFinProjet(projet.getFinProjet());
			projet2.setPhases(projet.getPhases());
			projet2.setResponsables(projet.getResponsables());
			projet2.setRisques(projet.getRisques());
			
			projetRepository.save(projet2);
		}
	}

	@Override
	public void deleateProjet(Long projetId) {
		Projet projet = projetRepository.findById(projetId).get();
		projetRepository.delete(projet);
	}

	@Override
	public Projet findProjetById(Long projetId) {
		Projet projet = projetRepository.findById(projetId).get();
		return projet;
	}

	@Override
	public List<Projet> findAllProjets() {
		
		return projetRepository.findAll();
	}

	@Override
	public Projet findByDateDebut(Date dateDebut) {
		Projet projet = projetRepository.findByDateDebut(dateDebut);
		return projet;
	}

	@Override
	public Projet importer(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projet export(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}

}