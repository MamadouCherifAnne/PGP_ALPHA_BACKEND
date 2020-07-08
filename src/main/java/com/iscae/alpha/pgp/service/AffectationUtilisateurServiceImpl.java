package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.AffectationUtilisateurRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;
import com.iscae.alpha.pgp.entities.Utilisateur;

@Service
public class AffectationUtilisateurServiceImpl implements AffectationUtilisateurService {
	
	@Autowired
	AffectationUtilisateurRepository userForJobRepo;

	@Override
	public AffectationUtilisateur addAffectationUser(AffectationUtilisateur userJob) {
		
		
		AffectationUtilisateur userForJob = new AffectationUtilisateur();
		userForJob.setUser_task(new UserToTache(userJob.getUser_task().getIdUser(), userJob.getUser_task().getIdTache()));
		userForJob.setTempsPasser(userJob.getTempsPasser());
		
		
		return userForJobRepo.save(userForJob);
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

		
		Optional<AffectationUtilisateur> verif = userForJobRepo.findById(idAffectation);
		if(verif.isPresent()) {
			userForJob = userForJobRepo.getOne(idAffectation);
			// Paasons a la suppression des valeurs
				userForJobRepo.deleteById(idAffectation);
			return true;
		}
		else {
			return false;
		}
	}

	
	// Afficher les affectations par Tache
	@Override
	public List<AffectationUtilisateur> getAffectationsForTache(Long idTache) {
		return userForJobRepo.getAffectationsForTache(idTache);
	}

	
	// Afficher les affections par Utilisateurs
	@Override
	public List<AffectationUtilisateur> getAffectationsForUser(Long idUser) {
		// TODO Auto-generated method stub
		return userForJobRepo.getAffectationsByUtilisateur(idUser);
	}

}
