package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.CommentaireRepository;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	CommentaireRepository commentRepo;
	@Autowired
	TacheService tacheService;
	@Autowired
	ProjetService projetService;

	@Override
	public Commentaire addComment(Commentaire comment) {
		// TODO Auto-generated method stub
		//Renseigner l'heure actuelle du commentaire
		System.out.println("La date actuelle ########:"+comment.getDateComment());
		//comment.setDateComment(new Date());
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

}
