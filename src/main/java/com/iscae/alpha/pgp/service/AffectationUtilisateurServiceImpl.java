package com.iscae.alpha.pgp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.AffectationUtilisateurRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;

@Service
public class AffectationUtilisateurServiceImpl implements AffectationUtilisateurService {
	
	@Autowired
	AffectationUtilisateurRepository userForJobRepo;

	@Override
	public AffectationUtilisateur addAffectationUser(AffectationUtilisateur userJob) {
		// TODO Auto-generated method stub
		AffectationUtilisateur userForJob = new AffectationUtilisateur();
		userForJob.setUser_task(new UserToTache(userJob.getUser_task().getIdUser(), userJob.getUser_task().getIdTache()));
		userForJob.setTempsPasser(userJob.getTempsPasser());
		
		
		return userForJobRepo.save(userForJob);
	}

	@Override
	public AffectationUtilisateur updateAffectationUser(AffectationUtilisateur userJob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAffectation(Long idUser, Long idTache) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AffectationUtilisateur> getAllAffectations() {
		// TODO Auto-generated method stub
		return userForJobRepo.findAll();
	}

}
