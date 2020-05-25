package com.iscae.alpha.pgp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Utilisateur;

public class UtilisateurServiceImplementation implements UtilisateurService{
	@Autowired
	UtilisateurRepository userRepository;

	@Override
	public Utilisateur addUtilisateur(Utilisateur user) {
		return userRepository.save(user);
		
		
	}

}
