package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Message;
import com.iscae.alpha.pgp.entities.Profession;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service
public class UtilisateurServiceImplementation implements UtilisateurService{
	@Autowired
	UtilisateurRepository userRepository;
	@ Autowired
	AffectationUtilisateurService affectService;
	@Autowired
	TacheService tacheService;
	@Autowired
	ProfessionRepository profRepo;
	
	
	@Override
	public Utilisateur addUser(Utilisateur user) {
		// Verification d'un utilisateur 

		Utilisateur use=userRepository.findByNom(user.getUsername());
		
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
			oldUser.setNom(user.getNom());
			
			// actualiser la profession
			if(user.getProfessions() !=null) {
				List<Profession> listprof =new ArrayList<>();
				
				for(Profession p:user.getProfessions()) {
				listprof.add(profRepo.findByNumProfession(p.getNumProfession()).get());
				}
			oldUser.setProfessions(listprof);	
			}
			
			
			////////////////////////////////////////////////////////////////////////////
			oldUser.setRapports(user.getRapports());
			oldUser.setCommentaires(user.getCommentaires());
			
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
				user.setProfessions(null);
			}
			
			// Verification si l'utilisateur n'est pas affecte dans une tache
			List<AffectationUtilisateur> affectations =affectService.getAffectationsForUser(id);
			if(affectations.isEmpty()) {
				userRepository.deleteById(id);
				return true;
				}
		}
			return false;
				
	}

	@Override
	public List<Utilisateur> getAllUsers() {
		if(userRepository.findAll()!=null) {
		return userRepository.findAll();
		}else
		{
			return null;
		}
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
		if(verifUser.isPresent()) {
			 
			 return verifUser.get();
		}else {
			return null;
		}

	}


	// Afficher la liste des Tache de L' utilisateur
	@Override
	public List<Tache> TacheToRealise(Long idUser) {
		List<Tache> tacheToRealise =new ArrayList<>();
		List<AffectationUtilisateur> affectations = new ArrayList<>();
		affectations = affectService.getAffectationsForUser(idUser);
		if(!affectations.isEmpty()) {
			for(AffectationUtilisateur af:affectations) {
				// On recupere l'identifiant de la tache puis on allimente la liste des tache
				Tache task =new Tache();
				task =tacheService.findTache(af.getUser_task().getIdTache());
				tacheToRealise.add(task);
			
			}
			// On ordonne la liste par ordre croissant de date de debut de tache
			Collections.sort(tacheToRealise, (x, y) -> x.getDebutTache().compareTo(y.getDebutTache()));
			
			return tacheToRealise;
		}
		return null;
	}



	@Override
	public List<Message> getAllSendedMessageFromUser(Long idUser) {
		// toutrs sles message envoyer
		Utilisateur user =this.getUserById(idUser);
		List <Message> messages = new ArrayList<>();
		if(user !=null) {
			messages =user.getSendMessages();
			//Ordonne les message par ordre croissant des date d'envoie
			Collections.sort(messages, (x, y) -> x.getDateEnvoie().compareTo(y.getDateEnvoie()));
			return messages;
		}else {
		return null;}
	}



	@Override
	public List<Message> getAllRecivedMessageFromUser(Long idUser) {
		// Afficher tout les essage recu
		Utilisateur user =this.getUserById(idUser);
		List <Message> messages = new ArrayList<>();
		if(user !=null) {
			messages =user.getMessageReceived();
			//Ordonne les message par ordre croissant des date d'envoie
			Collections.sort(messages, (x, y) -> x.getDateEnvoie().compareTo(y.getDateEnvoie()));
			return messages;
		}else {
		return null;}
	}
	
	// Recherche par usrname
	
	@Override
	public Utilisateur getUserByUsername(String username) {
		Utilisateur user = null;
		if(userRepository.findByUsername(username).isPresent()) {
			user =userRepository.findByUsername(username).get();
		}

		return user;
	}



}
   