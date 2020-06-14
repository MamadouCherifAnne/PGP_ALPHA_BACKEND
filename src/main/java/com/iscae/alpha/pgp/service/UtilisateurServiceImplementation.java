package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service
public class UtilisateurServiceImplementation implements UtilisateurService{
	@Autowired
	UtilisateurRepository userRepository;
		
	
	@Override
	public Utilisateur addUser(Utilisateur user) {
		// Verification d'un utilisateur 
		
		return userRepository.save(user) ;
		}
		
	

	@Override
	public Utilisateur updateUser(Utilisateur user) {
		
		//Verifier si l'utilisateur existe puis changer les valuers a modifier
		Optional<Utilisateur> oldUser= userRepository.findById(user.getIdUser());
		
		// Set new Values
		if(oldUser!=null) {
			oldUser.get().setAdresse(user.getAdresse());
			oldUser.get().setEmail(user.getEmail());
			oldUser.get().setTelephone(user.getTelephone());
			oldUser.get().setPrenom(user.getPrenom());
			
			if(userRepository.findByNom(user.getNom())==null) {
			oldUser.get().setNom(user.getNom());
			}
		}
		
		
		return userRepository.save(oldUser.get());
	}
	

	@Override
	public boolean deleteUser(Long id) {
		
		// Verifier si lutilisqteur existe dabord
		try {		
			userRepository.deleteById(id);
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
		
		
	}

	@Override
	public List<Utilisateur> getAllUsers() {
		
		return userRepository.findAll();
	}

	
	//Recherche d'un utilisateur par son nom
	@Override
	public Utilisateur getUserByName(String prenom) {
		
	
			return userRepository.findByNom(prenom).get();
	}

}
