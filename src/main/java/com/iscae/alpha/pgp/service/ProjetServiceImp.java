package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;


@Service 

public class ProjetServiceImp implements ProjetService{
	
	@Autowired
	ProjetRepository projetRepository;
	@Autowired 
	CommentaireService commentService;

	
	@Override
	public Projet AddProjet(Projet projet) {
		return projetRepository.save(projet);
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
			projet2.setResponsables(projet.getResponsables());
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
		return projetRepository.projectTasks(numProjet);
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
		Projet projet =findProjetById(idProjet);
		if(projet != null) {
			return projet.getCommentaires();
		}
		
		return null;
	}


}
