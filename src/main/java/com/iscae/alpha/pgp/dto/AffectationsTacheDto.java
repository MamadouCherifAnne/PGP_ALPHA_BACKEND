package com.iscae.alpha.pgp.dto;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;

public class AffectationsTacheDto {
	private AffectationUtilisateur affectation;
	private Utilisateur ressources;
	private Tache tache;
	
	
	
	public AffectationsTacheDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AffectationUtilisateur getAffectation() {
		return affectation;
	}
	public void setAffectation(AffectationUtilisateur affectation) {
		this.affectation = affectation;
	}
	public Utilisateur getRessources() {
		return ressources;
	}
	public void setRessources(Utilisateur ressources) {
		this.ressources = ressources;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	
	
}
