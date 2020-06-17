package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Profession;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service
public class UtilisateurServiceImplementation implements UtilisateurService{
	@Autowired
	UtilisateurRepository userRepository;
	@Autowired
	ProfessionRepository profRepo;
		
	
	@Override
	public Utilisateur addUser(Utilisateur user) {
		// Verification d'un utilisateur 
		Utilisateur use=userRepository.findByNom(user.getNom());
		
		if(use==null) {
			/*
			 *  verification que la liste des profession nest pas null puis actualiser la liste des utilisateur
			 *  de chacune de ces professions
			 */
			if(user.getProfessions() !=null) {
						List<Profession> listprof =new ArrayList<>();
						
						for(Profession p:user.getProfessions()) {
						listprof.add(profRepo.findByNumProfession(p.getNumProfession()).get());
						}
					user.setProfessions(listprof);	
			}
			return userRepository.save(user) ;}
		else {return null;}
		
		
		}
		
	

	@Override
	public Utilisateur updateUser(Utilisateur user) {
		
		//Verifier si l'utilisateur existe puis changer les valuers a modifier
			Utilisateur oldUser= userRepository.getOne(user.getIdUser());
		
		// Set new Values
		if(oldUser!=null) {
			oldUser.setAdresse(user.getAdresse());
			oldUser.setEmail(user.getEmail());
			oldUser.setTelephone(user.getTelephone());
			oldUser.setPrenom(user.getPrenom());
			oldUser.setRole(user.getRole());
			oldUser.setProfessions(user.getProfessions());
			oldUser.setRapport(user.getRapport());
			oldUser.setCommentaire(user.getCommentaire());
			
			if(userRepository.findByNom(user.getNom())==null) {
			oldUser.setNom(user.getNom());
			}
		}
		
		
		return userRepository.save(oldUser);
	}
	

	@Override
	public boolean deleteUser(Long id) {
		
		// Verifier si lutilisqteur existe dabord
		Utilisateur user = new Utilisateur();
		Optional<Utilisateur> verifUser = userRepository.findByIdUser(id);
		if(verifUser != null) {
			 user = userRepository.getOne(id);
			
			// Supprimer l'utilisateur de la liste des utilisateur dans les profession avec lesquelles il etait liee
			if(user.getProfessions()!= null) {
			for (Profession p : user.getProfessions()) {
				p.getUtilisateurs().remove(user);
			}
			}
			// Suppression de L'utilisateur dans la liste de role avec aui il est en relation
			if(user.getRole() !=null) {
				user.getRole().getUsers().remove(user);
			}
				userRepository.deleteById(id);
				return true;
				}else {
					return false;
				}
	}

	@Override
	public List<Utilisateur> getAllUsers() {
		
		return userRepository.findAll();
	}

	
	//Recherche d'un utilisateur par son nom
	@Override
	public Utilisateur getUserByName(String prenom) {
		
			return userRepository.findByNom(prenom);
	}



	@Override
	public Utilisateur getUserById(Long id) {
		Utilisateur user = new Utilisateur();
		Optional<Utilisateur> verifUser = userRepository.findByIdUser(id);
		if(verifUser != null) {
			 user = userRepository.getOne(id);
			 return user;
		}else {
			return null;
		}
	}

}
