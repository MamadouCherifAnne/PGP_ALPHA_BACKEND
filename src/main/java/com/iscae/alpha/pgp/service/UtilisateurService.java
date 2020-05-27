package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.Utilisateur;

public interface UtilisateurService {
	
	Utilisateur AddUser(Utilisateur user); 
	void deleateUser(Long userId);
	Utilisateur findUserById(Long userId);
	List<Utilisateur> getdAllUser();
	Utilisateur getUserByMail(String userEmail);
}
