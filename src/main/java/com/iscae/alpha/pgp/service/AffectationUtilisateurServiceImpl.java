package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.AffectationUtilisateurRepository;
import com.iscae.alpha.pgp.dto.AffectationsTacheDto;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.UserToTache;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.mail.Mail;

@Service
public class AffectationUtilisateurServiceImpl implements AffectationUtilisateurService {
	
	@Autowired
	AffectationUtilisateurRepository userForJobRepo;
	@Autowired
	UtilisateurService userService;
	
	@Autowired
	TacheService tacheService;
	@Autowired
	MailServiceInterface mailService;
	
	private static final Logger log =LoggerFactory.getLogger(AffectationUtilisateurServiceImpl.class);

	@Override
	public AffectationUtilisateur addAffectationUser(AffectationUtilisateur userJob) {
		
		AffectationUtilisateur affect = new AffectationUtilisateur();
		AffectationUtilisateur userForJob = new AffectationUtilisateur();
		// Reparation sur la maniere dajuter d affectations
		UserToTache idAffect =  new UserToTache(userJob.getUser_task().getIdUser(), userJob.getUser_task().getIdTache());
		userForJob.setUser_task(idAffect);
		userForJob.setTempsPasser(userJob.getTempsPasser());
		Optional<AffectationUtilisateur> verifExistance = userForJobRepo.findById(idAffect);
		if(!verifExistance.isPresent()) {
			Utilisateur user =  userService.getUserById(idAffect.getIdUser());
			
			// ENvoyer un message a un a l utilisateur
			String [] to = new String [1];
			to[0]=user.getEmail();
			//to[0] = "mmdanne98@gmail.com";
			Mail mail = new Mail();
			mail.setTo(to);
			mail.setSubject("Affectation à une tache");
			mail.setBody("Vous avez été affecter une  tache.\n Voici le Lien "+"http://localhost:4200/task/"+idAffect.getIdTache());
			affect = userForJobRepo.save(userForJob);
			mailService.SendMessage(mail);
			return affect;
		}
		return null;
		
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

	@Override
	public Collection<AffectationsTacheDto> getAffectationsForTacheFormater(Long idTache) {
		 Collection<AffectationsTacheDto> affectations = new ArrayList<>();
		if(userForJobRepo.getAffectationsForTache(idTache)!=null) {
			List<AffectationUtilisateur> taskAffects = userForJobRepo.getAffectationsForTache(idTache);
			// J'applique une sorte de formatage pour l'approprier a l'affichage
			for (AffectationUtilisateur afectTache : taskAffects) {
				AffectationsTacheDto afectDto = new AffectationsTacheDto();
				afectDto.setAffectation(afectTache);
				Utilisateur user = userService.getUserById(afectTache.getUser_task().getIdUser());
				if(user != null) {
					afectDto.setRessources(user);
					afectDto.setAffectation(afectTache);
					affectations.add(afectDto);
				}else {
					this.deleteAffectation(afectTache.getUser_task().getIdUser(), idTache);
				}
			}
			return affectations;
		}
		return null;
	}

	@Override
	public Collection<AffectationsTacheDto> getLatestAffectationsForUser(String username) {
		
		Date limiteDate = new Date(System.currentTimeMillis()-24*60*60*1000*3);
		Date today = new Date(System.currentTimeMillis());
		log.info("La date d'hier"+limiteDate+" La date de today is"+today);
		Utilisateur user = userService.getUserByUsername(username);
		Collection<AffectationsTacheDto> affectations = new ArrayList<>();
		if(user!= null) {
		 
			if(userForJobRepo.getAffectationsByUtilisateur(user.getIdUser())!=null) {
				List<AffectationUtilisateur> taskAffects = userForJobRepo.getAffectationsByUtilisateur(user.getIdUser());
				// J'applique une sorte de formatage pour l'approprier a l'affichage
				for (AffectationUtilisateur afectTache : taskAffects) {
					
					if(afectTache.getDateAffectation().after(limiteDate)) {
						AffectationsTacheDto afectDto = new AffectationsTacheDto();
						Tache task = tacheService.findTache(afectTache.getUser_task().getIdTache());
						if(task!=null) {
						afectDto.setAffectation(afectTache);
						afectDto.setRessources(user);
						afectDto.setAffectation(afectTache);
						afectDto.setTache(task);
						affectations.add(afectDto);
						}
					}
				}
			}
			log.info("Les taches dont il est affecter avant 23 jours sont "+affectations.size());
			
		}
		return affectations;
	}

}
