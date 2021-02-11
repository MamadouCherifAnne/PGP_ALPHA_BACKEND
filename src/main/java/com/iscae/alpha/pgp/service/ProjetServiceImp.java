package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.ProjectUtilisateursRepository;
import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.dto.MembreProjetDto;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.ProjectUserID;
import com.iscae.alpha.pgp.entities.ProjectUtilisateurs;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service 

public class ProjetServiceImp implements ProjetService{
	
	@Autowired
	ProjetRepository projetRepository;
	@Autowired 
	CommentaireService commentService;
	@Autowired
	UtilisateurService userService;
	@Autowired
	ProjectUtilisateursRepository projetMembreRepo;
	
	@Autowired
	TacheService tacheService;
	
	private final static Logger log= LoggerFactory.getLogger(ProjetServiceImp.class);
	
	@Override
	public Projet AddProjet(Projet projet) {
		Projet project = projetRepository.save(projet);
		ProjectUtilisateurs membre = new ProjectUtilisateurs();
		ProjectUserID  idMembre = new ProjectUserID();
		idMembre.setIdProjet(project.getNumProjet());
		idMembre.setIdUser(project.getResponsable().getIdUser());
		membre.setIdMembre(idMembre);
		membre.setRole("responsable");
		this.addMembreToProject(membre);
		return null;
	}

	@Override

	public boolean updateProjet(Projet projet) {

		Optional<Projet> projet1 = projetRepository.findById(projet.getNumProjet());
		
		if(projet1 != null) {
			Projet projet2 = projet1.get();
			projet2.setNumProjet(projet.getNumProjet());
			projet2.setNomProjet(projet.getNomProjet());
			projet2.setDebutProjet(projet.getDebutProjet());
			
			projet2.setDescription(projet.getDescription());
			projet2.setZoneRealisation(projet.getZoneRealisation());
			projet2.setFinProjet(projet.getFinProjet());
			projet2.setPhases(projet.getPhases());
			projet2.setResponsable(projet.getResponsable());
			projet2.setRisques(projet.getRisques());
			
			projetRepository.save(projet2);

			return true;
		}
		return false;

	}

	@Override
	public void deleateProjet(Long projetId) {
		Projet projet = projetRepository.findById(projetId).get();
		projetRepository.delete(projet);
	}

	@Override
	public Projet findProjetById(Long projetId) {
		
		//Verification de l'existance d'un projet si oui on le retourne sinon on retourne null;
		if (projetRepository.findById(projetId).isPresent()) {
			Projet projet = projetRepository.findById(projetId).get();
			
			return projet;
		}
		
		return null;
	}

	@Override
	public List<Projet> findAllProjets() {
		
		return projetRepository.findAll();
	}

	@Override
	public Projet findByDateDebut(Date dateDebut) {
		Projet projet = projetRepository.findByDebutProjet(dateDebut);
		return projet;
	}

	@Override
	public Projet importer(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projet export(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projet findByDateFin(Date dateFin) {
		Projet projet = projetRepository.findByFinProjet(dateFin);
		return projet;
	}

	@Override
	public List<Phase> listPhaseProjet(Long idProjet) {
		Projet projet = projetRepository.findById(idProjet).get();
		return projet.getPhases();
	}

	@Override
	public List<Tache> projectTasks(Long numProjet) {
		// TODO Auto-generated method stub
		if(this.findProjetById(numProjet)!=null) {
			Projet p = this.findProjetById(numProjet);
			List<Tache> taches = new ArrayList<>();
			for(Phase ph:p.getPhases()) {
				for(Tache tache:ph.getTache()) {
					if(tache.getType()==null || !tache.getType().equals("Jalon") ) {
						taches.add(tache);
					}
				}
			}
			
		return taches;
		}
		return new ArrayList<>();
	}

	@Override
	public List<Tache> getAllJalons(Long numProjet) {
		// TODO Auto-generated method stub
		return projetRepository.allProjectJalons(numProjet);
	}

	@Override
	public List<Tache> getRetardJalon(Long numProjet) {
		// TODO Auto-generated method stub
		List<Tache> jalons = new ArrayList<>();
		jalons = this.getAllJalons(numProjet);
		return null;
	}

	@Override
	public Projet addcommentsToProject(List<Commentaire> comments) {
		// Ajout de plusieur commentaire a meme temps
		if(!comments.isEmpty()) {
			Projet projet=new Projet();
			for(Commentaire com : comments) {
				Commentaire coment = commentService.addComment(com);
				projet = coment.getProjetComment();
			}
			return projet;
		}
		return null;
	}

	@Override
	public List<Commentaire> allCommentsOfProject(Long idProjet) {
		// Afficher la liste des commentaires d'un projet
		List<Commentaire> comments = new ArrayList<>();
		Projet projet =findProjetById(idProjet);
		if(projet != null) {
			
			comments = projet.getCommentaires();
			Collections.sort(comments, (y, x) -> x.getDateComment().compareTo(y.getDateComment()));
			return comments ;
		}
		
		return comments;
	}

	@Override
	public boolean addMembreToProject(ProjectUtilisateurs membreProjet) {
		// TODO Auto-generated method stub
		ProjectUtilisateurs projetMembre = new ProjectUtilisateurs();
		ProjectUserID projectMembreId = new ProjectUserID();
		
		if(membreProjet.getIdMembre().getIdProjet() != null && membreProjet.getIdMembre().getIdUser() != null) {
			projetMembreRepo.save(membreProjet);
			return true;
		}
		
		return false;
	}

	@Override
	public Collection<MembreProjetDto> getMembreOfProject(Long id) {
		// TODO Auto-generated method stub
		Collection<MembreProjetDto> membres = new ArrayList<>();
		List<ProjectUtilisateurs> projetMembres = new ArrayList<>();
		projetMembres = projetMembreRepo.getProjectsMembres(id);
		if(projetMembres != null) {
				for(ProjectUtilisateurs projetMembre : projetMembres ) {
					Utilisateur user = userService.getUserById(projetMembre.getIdMembre().getIdUser());
					MembreProjetDto userDto = new MembreProjetDto();
					
					userDto.setIdUser(user.getIdUser());
					userDto.setUsername(user.getUsername());
					userDto.setNom(user.getNom());
					userDto.setPrenom(user.getPrenom());
					userDto.setActif(false);
					userDto.setTelephone(user.getTelephone());
					userDto.setEmail(user.getEmail());
					userDto.setAdresse(user.getAdresse());
					userDto.setCompany(user.getCompany());
					userDto.setProjectRole(projetMembre.getRole());
					membres.add(userDto);
				}
				
			}
		
			return membres;
		}

	@Override
	public int verifRoleMembre(Long idProject, Long idUser) {
		//  Verification du role d'un utilisateur  sur un projet
		int role=-1;
		Collection<MembreProjetDto> membresProject= this.getMembreOfProject(idProject);
		if(membresProject != null && membresProject.size() > 0) {
			for(MembreProjetDto membre : membresProject) {
				if(membre.getIdUser() == idUser && membre.getProjectRole() != null) {
					if(membre.getProjectRole().equalsIgnoreCase("collaborateur") || membre.getProjectRole().equalsIgnoreCase("acteur")) {
					role =1;
					break;
					}
					else if( membre.getProjectRole().equalsIgnoreCase("responsable")){
						role =2;
						break;
					}
				}
			}
		}
		return role;
		
	}

	@Override
	public boolean deleteMembreOfProject(ProjectUserID idMembre) {
		boolean reponse = false;
		boolean isAffectedToTach = false;
		// suppression d'un membre dans le projet
		Utilisateur user= userService.getUserById(idMembre.getIdUser());
		if(user != null) {
			List<Projet> projectsUser = userService.getMyProjects(user.getUsername());
			if(projectsUser != null && !projectsUser.isEmpty()) {
				for (Projet projet : projectsUser) {
					if(projet.getNumProjet() == idMembre.getIdProjet()) {
						isAffectedToTach = true;
						break;
					}
				}
			}
			// Verifier si l'utilisateur  n'est pas lier a une tache dans ce projet
			if(isAffectedToTach == false) {
				projetMembreRepo.deleteById(idMembre);
				reponse = true;
			}
		}
		return reponse;
		
	}

	@Override
	public Utilisateur getProjectOwner(Long IdProject) {
		// Afficher le chef du projet 
		Projet projet = this.findProjetById(IdProject);
		if(projet != null) {
			return projet.getResponsable();
		}else {
			return null;
		}
	}

	@Override
	public double coutTotalProject(Long idProject) {
		double totale=0;
		List<Tache> projectTsaks = new ArrayList<>();
		// Calcul de la somme des cout de chaque taches
		Projet projet = this.findProjetById(idProject);
		if(projet != null) {
			projectTsaks= this.projectTasks(idProject);
			for (Tache tache : projectTsaks) {
				totale = totale + tacheService.totalCoutTask(tache.getNumTache());
			}
		}
		return totale;
	}

	@Override
	public double coutProjectLastMonth(Long idProject) {
		// Afficher le cout du Projet pendant 6 mois
		Date limiteDate = new Date(System.currentTimeMillis()-24*60*60*1000*20);
		Date today = new Date(System.currentTimeMillis());
		log.info("La date d'hier"+limiteDate+" La date de today is"+today);
		long timer =(today.getTime()-limiteDate.getTime())/(1000 *3600*24);
		System.out.println("VOICI LA DATE DE 6 MOIS AVANT ::::#####"+timer);
		return 0;
	}
	


}
