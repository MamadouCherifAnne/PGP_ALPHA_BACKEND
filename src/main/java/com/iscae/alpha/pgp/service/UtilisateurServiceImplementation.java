package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.dao.ProjectUtilisateursRepository;
import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.dto.InfosMessageNonLu;
import com.iscae.alpha.pgp.dto.MonTravail;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Message;
import com.iscae.alpha.pgp.entities.Profession;
import com.iscae.alpha.pgp.entities.ProjectUtilisateurs;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.mail.ConstantEnvoiMail;
import com.iscae.alpha.pgp.mail.Mail;


@Service
public class UtilisateurServiceImplementation implements UtilisateurService{
	@Autowired
	UtilisateurRepository userRepository;
	@ Autowired
	AffectationUtilisateurService affectService;
	@Autowired
	TacheService tacheService;
	@Autowired
	MessageService msgService;
	@Autowired
	ProfessionRepository profRepo;
	@Autowired
	MailServiceInterface mailService;
	@Autowired
	ProjectUtilisateursRepository projUserRepo;
	@Autowired
	ProjetService projService;
	/*@Autowired
	PasswordEncoder bCryptPasswordEncoder;*/
	
	
	@Override
	public Utilisateur addUser(Utilisateur user) {
		// Verification d'un utilisateur 

		Optional<Utilisateur> use=userRepository.findByUsername(user.getUsername());
		
		if(!use.isPresent()) {
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
			//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			Utilisateur util = userRepository.save(user);
			String [] to = new String [1];
			to[0]=user.getEmail();
			//to[0] = "mmdanne98@gmail.com";
			Mail mail = new Mail();
			mail.setTo(to);
			mail.setSubject("Nouvel Invitation");
			mail.setBody(ConstantEnvoiMail.notificationAJoutUser+" dans L'entreprise "+user.getCompany()+""
					+ " avec le mot de passe :"+user.getPassword()+"\n\n vous êtes recommandé de changer de mot de passe "
							+ " après la première connexion.\n On vous souhaite bienvenue dans le plateforme");
			
			mailService.SendMessage(mail);
			return util ;}
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
			
			oldUser.setUsername(user.getUsername());

			
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
		if(user !=null && !user.getMessageReceived().isEmpty()) {
			for(Message msg : user.getMessageReceived()) {
				if(msg.isMessageLu()==false) {
				msgService.modifierEtat(msg.getIdMessage());
				msg.setMessageLu(true);
				}
				messages.add(msg);
				
			}
			//Ordonne les message par ordre croissant des date d'envoie
			Collections.sort(messages, (y, x) -> x.getDateEnvoie().compareTo(y.getDateEnvoie()));
			
		}
		return messages;
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



	@Override
	public Entreprise getUserEntreprise(Long idUser) {
		if(this.getUserById(idUser)!=null) {
			return userRepository.getOne(idUser).getEntreprise();
		}
		return null;
	}



	@Override
	public List<Projet> getMyProjects(String username) {
		// TODO Auto-generated method stub
		if(this.getUserByUsername(username)!=null) {
			Utilisateur user= this.getUserByUsername(username);
			List<Projet> projects = user.getProjets();
			if(this.TacheToRealise(user.getIdUser())!=null) {
				List<Tache> taches = this.TacheToRealise(user.getIdUser());
				for (Tache tache : taches) {
					if(!projects.contains(tache.getPhase().getProjet()))
					projects.add(tache.getPhase().getProjet());
				}
			}
			return projects;
		}
		return null;
	}



	// Afficher les taches a realiser par un seul utilisateur
	@Override
	public List<MonTravail> tasksUser(Long idUser) {
		List<Tache> userTasks= TacheToRealise(idUser);
		List<MonTravail> mestaches = new ArrayList<MonTravail>();
		
		MonTravail l = new MonTravail();
		if(userTasks != null) {
			for(Tache tache: userTasks) {
				MonTravail montravail = new MonTravail();
				montravail.setTache(tacheService.findTache(tache.getNumTache()));
				montravail.setPhase(tache.getPhase());
				montravail.setProjet(tache.getPhase().getProjet());
				mestaches.add(montravail);
			}
		}
		l.listMontravail(mestaches);
		return mestaches;
	}

	@Override
	public InfosMessageNonLu getMessageNonLu(String username) {
		int cmpt = 0;
		InfosMessageNonLu msgNonLu = new InfosMessageNonLu();
		Utilisateur user =this.getUserByUsername(username);
		msgNonLu.setIdUser(user.getIdUser());
		List<Message> messagesNonLus = new ArrayList<>();
		if(user !=null && !user.getSendMessages().isEmpty()) {
		for(Message msg : user.getMessageReceived()) {
			if(msg.isMessageLu() !=true) {
				
				cmpt++;
				}
			}
				msgNonLu.setMsgNonLu(cmpt);
			}
		
		return msgNonLu;
		}



	@Override
	public List<Projet> getProjectOfUser(String username) {
		Utilisateur user= this.getUserByUsername(username);
		
		Collection<ProjectUtilisateurs> myProjectAffect = new ArrayList<>();
		myProjectAffect = projUserRepo.getUserProjects(user.getIdUser());
		List<Projet> mesProjets = new ArrayList<>();
		if(!myProjectAffect.isEmpty()) {
			for (ProjectUtilisateurs p : myProjectAffect) {
				Projet projet = projService.findProjetById(p.getIdMembre().getIdProjet());
				if(projet != null) {
					mesProjets.add(projet);
				}
			}
		}
		return mesProjets;
	}

	public int existeTacheNotfinish(Long numProjet) {
		//verifie s'il ya des taches en cours 
		int val = 0;
		Date now = new Date();
		List<Tache> tasks = projService.projectTasks(numProjet);
		if(tasks != null) {
			for(Tache tache: tasks) {
				if(tache.getTauxAvancement() != 100) {
					val = val + 1;
					break;
				}
			}
		}
		return val;
	}

	@Override
	public List<Projet> getProjetsEncours(String username) {
		// liste des projets en cours 
		List<Projet> mesProjets = this.getProjectOfUser(username);
		List<Projet> projetsEncours = new ArrayList<>();
		Date now = new Date();
		if(mesProjets != null) {
			for(Projet p: mesProjets) {
				if(p.getFinProjet().after(now) && existeTacheNotfinish(p.getNumProjet()) == 1) {
					projetsEncours.add(p);
				}
			}
		}
		return projetsEncours;
	}

//......................................................................................................
	@Override
	public List<Projet> getProjetEnretard(String username) {
		// liste des projets en retard
		List<Projet> mesProjets = this.getProjectOfUser(username);
		List<Projet> projetsEnRetard = new ArrayList<>();
		Date now = new Date();
		if(mesProjets != null) {
			for(Projet p: mesProjets) {
				if(p.getFinProjet().before(now) && existeTacheNotfinish(p.getNumProjet()) == 1) {
					projetsEnRetard.add(p);
				}
			}
		}
		return projetsEnRetard;
	}


//.........................................................................................................
	@Override
	public List<Projet> getProjetTermines(String username) {
		//liste des projets terminés
		List<Projet> mesProjets = this.getProjectOfUser(username);
		List<Projet> projetsTerminees = new ArrayList<>();
		Date now = new Date();
		if(mesProjets != null) {
			for(Projet p: mesProjets) {
				if(existeTacheNotfinish(p.getNumProjet()) == 0) {
					projetsTerminees.add(p);
				}
			}
		}
		return projetsTerminees;
	}


}
   