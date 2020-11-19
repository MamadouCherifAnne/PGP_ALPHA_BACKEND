package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.Commentaire;

public interface CommentaireService {
	// Ajout d'un commentaire
	public Commentaire addComment(Commentaire comment);
	
	// Afficher les commentaire d'une tache
	
	public  List<Commentaire> getCommentsOfTask(Long idTache);
	
	// Afficher tout les commentaire sur un projet
	public List<Commentaire> getCommentsOfProject(Long idProjet);

	// Supprimer un commentaire
	public boolean deleteComment(Long idComment);
}
