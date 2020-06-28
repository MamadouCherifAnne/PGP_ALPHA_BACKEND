package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;

public interface AffectationUtilisateurService {
	
	public AffectationUtilisateur addAffectationUser(AffectationUtilisateur userJob);
	public AffectationUtilisateur updateAffectationUser( AffectationUtilisateur userJob);
	public boolean deleteAffectation(Long idUser,Long idTache);
	public List<AffectationUtilisateur> getAllAffectations();

}
