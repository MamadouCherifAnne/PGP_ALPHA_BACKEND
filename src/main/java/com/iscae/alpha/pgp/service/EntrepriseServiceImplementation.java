package com.iscae.alpha.pgp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.EntreprisesRepository;
import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Projet;


@Service

public class EntrepriseServiceImplementation implements EntrepriseService {
	
	private static final Logger Log =LoggerFactory.getLogger(EntrepriseServiceImplementation.class);
	
	@Autowired
	EntreprisesRepository entrepriseRepo;
	@Autowired
	UtilisateurService userService;

	@Override
	public Entreprise addEntreprise(Entreprise proprietaire) {
		Log.debug("Verification qu'il nexiste pas une entreprise de meme nom dans la structure ");
		if(!entrepriseRepo.findByNameEntreprise(proprietaire.getNameEntreprise()).isPresent()) {
			Log.info("Ajout de l'entreprise avec succees");
			return entrepriseRepo.save(proprietaire);
		}
		Log.info("Redondance de nom donc echec d'enregistrement");
		return null;
	}

	@Override
	public Entreprise updateEntreprise(Entreprise entreprise) {
		
		if(!entrepriseRepo.findByNameEntreprise(entreprise.getNameEntreprise()).isPresent()) {
			Entreprise ep= new Entreprise();
			ep.setIdEntreprise(entreprise.getIdEntreprise());
			ep.setAdresse(entreprise.getAdresse());
			ep.setDomaine_Entreprise(entreprise.getDomaine_Entreprise());
			ep.setNameEntreprise(entreprise.getNameEntreprise());
			return entrepriseRepo.save(ep);
			
		}
		else {
			return null;
		}
	}

	@Override
	public boolean deleteEntreprise(Long idEntreprise) {
		// Suppression d'une entreprise
		
		Log.debug("Verification de la validité des données");
		if(entrepriseRepo.findById(idEntreprise).isPresent()) {
			entrepriseRepo.deleteById(idEntreprise);
			Log.info("suppression de l'entreprise avec succées");
			return true;
			
		}
		Log.info("echec de la suppression de l'entreprise");
		return false;
		
	}

	@Override
	public Entreprise findEntrepriseById(Long idEntreprise) {
		if(entrepriseRepo.findById(idEntreprise).isPresent()) {
			return  entrepriseRepo.getOne(idEntreprise);
		}
		return null;
	}

	@Override
	public List<Entreprise> findAllEntreprise() {
		// TODO Auto-generated method stub
		return entrepriseRepo.findAll();
	}

	@Override
	public List<Projet> getEntrepriseProjects(Long idEntreprise) {
		if(this.findEntrepriseById(idEntreprise)!=null) {
			List<Projet> projects = findEntrepriseById(idEntreprise).getProjets();
			return projects;
		}
		return null;
	}

	

}
