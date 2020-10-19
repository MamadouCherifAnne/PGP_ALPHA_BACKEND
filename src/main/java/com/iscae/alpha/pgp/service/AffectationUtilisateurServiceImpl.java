package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.AffectationUtilisateurRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;

@Service
public class AffectationUtilisateurServiceImpl implements AffectationUtilisateurService {
	
	@Autowired
	AffectationUtilisateurRepository userForJobRepo;
	
	private static final Logger log =LoggerFactory.getLogger(AffectationUtilisateurServiceImpl.class);

	@Override
	public AffectationUtilisateur addAffectationUser(AffectationUtilisateur userJob) {
		
		
		AffectationUtilisateur userForJob = new AffectationUtilisateur();
		// Reparation sur la maniere dajuter d affectations
		UserToTache idAffect =  new UserToTache(userJob.getUser_task().getIdUser(), userJob.getUser_task().getIdTache());
		userForJob.setUser_task(idAffect);
		userForJob.setTempsPasser(userJob.getTempsPasser());
		Optional<AffectationUtilisateur> verifExistance = userForJobRepo.findById(idAffect);
		if(!verifExistance.isPresent()) {
		return userForJobRepo.save(userForJob);
		}else {
			return null;
		}
	}

	@Override
	public AffectationUtilisateur updateAffectationUser(AffectationUtilisateur userJob) {
		AffectationUtilisateur userForJob = new AffectationUtilisateur();
		UserToTache idAffectation = new UserToTache();
		idAffectation.setIdUser(userJob.getUser_task().getIdUser());
		idAffectation.setIdTache(userJob.getUser_task().getIdTache());

		
		Optional<AffectationUtilisateur> verif = userForJobRepo.findById(idAffectation);
		if(verif != null) {
			userForJob = userForJobRepo.getOne(idAffectation);
			// Paasons a la modification des valeurs
			userForJob.setTempsPasser(userJob.getTempsPasser());
			userForJob.setCoutParHeure(userJob.getCoutParHeure());
			userForJob.setTempsEffectuer(userJob.getTempsEffectuer());
			
			return userForJobRepo.save(userForJob);
		}
		else {
			return null;
		}
	}

	@Override
	public boolean deleteAffectation(Long idUser, Long idTache) {
		// Supprimer une affectation de ressource a une tache
		AffectationUtilisateur userForJob = new AffectationUtilisateur();
		UserToTache idAffectation = new UserToTache();
		idAffectation.setIdUser(idUser);
		idAffectation.setIdTache(idTache);
		 log.info("ENTRER DANS LA METHODE DE SUPPRESSION D'UNE  AFFECTATION ");
		
		Optional<AffectationUtilisateur> verif = userForJobRepo.findById(idAffectation);
		if(verif.isPresent()) {
			userForJob = userForJobRepo.getOne(idAffectation);
			// Paasons a la suppression des valeurs
			log.info("SUPPRESSION AVEC SUCCES");
				userForJobRepo.delete(userForJob);
			return true;
		}
		else {
			log.info("ON ARRIVE PAS A SUPPRIMER CETTE AFFECTATION"+idAffectation.getIdTache()+"  "+idAffectation.getIdUser());
			return false;
		}
	}

	
	// Afficher les affectations par Tache
	@Override
	public List<AffectationUtilisateur> getAffectationsForTache(Long idTache) {
		if(userForJobRepo.getAffectationsForTache(idTache)!=null) {
			return userForJobRepo.getAffectationsForTache(idTache);
		}
		return null;
	}

	
	// Afficher les affections par Utilisateurs
	@Override
	public List<AffectationUtilisateur> getAffectationsForUser(Long idUser) {
		// TODO Auto-generated method stub
		return userForJobRepo.getAffectationsByUtilisateur(idUser);
	}

	@Override
	public AffectationUtilisateur getAffectationById(Long idTache, long idUser) {
		//Recherche par id Affectation
		UserToTache idAffectation =new UserToTache();
		idAffectation.setIdTache(idTache);
		idAffectation.setIdUser(idUser);
		Optional<AffectationUtilisateur> verif = userForJobRepo.findById(idAffectation);
		if(verif.isPresent()) {
			return verif.get();
		}
		return null;
	}

}
