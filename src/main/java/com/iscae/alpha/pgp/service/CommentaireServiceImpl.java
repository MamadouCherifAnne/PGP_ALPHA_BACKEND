package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.CommentaireRepository;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	CommentaireRepository commentRepo;
	@Autowired
	TacheService tacheService;
	@Autowired
	ProjetService projetService;
	@Autowired
	UtilisateurService userService;
	
	private static final Logger Log =LoggerFactory.getLogger(CommentaireServiceImpl.class);

	@Override
	public Commentaire addComment(Commentaire comment) {
		// TODO Auto-generated method stub
		//Renseigner l'heure actuelle du commentaire
		System.out.println("La date actuelle ########:"+comment.getDateComment());
		//comment.setDateComment(new Date());
		Log.info("Voici le createur"+comment.getUser().getUsername());
		Utilisateur user = userService.getUserById(comment.getUser().getIdUser());
		Log.info("Voici le commentateur"+user);
		comment.setUser(user);
		return commentRepo.save(comment);
	}

	@Override
	public List<Commentaire> getCommentsOfTask(Long idTache) {
		// TODO Auto-generated method stub
		Tache tache =  tacheService.findTache(idTache);
		if(tache != null) {
			return tache.getCommentaires();
		}
		return  null;
	}

	@Override
	public List<Commentaire> getCommentsOfProject(Long idProjet) {
		// TODO Auto-generated method stub
		Projet projet =projetService.findProjetById(idProjet);
		if(projet !=null) {
			return projet.getCommentaires();
		}
		return null;
	}

	@Override
	public boolean deleteComment(Long idComment) {
		// TODO Auto-generated method stub
		Optional<Commentaire> comment =commentRepo.findById(idComment);
		if(comment.isPresent()) {
			Commentaire com  = comment.get();
			commentRepo.deleteById(idComment);
			return true;
		}
		return false;
	}

}
