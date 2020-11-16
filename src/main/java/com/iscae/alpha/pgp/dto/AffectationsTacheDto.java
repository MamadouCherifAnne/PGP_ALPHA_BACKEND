package com.iscae.alpha.pgp.dto;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Utilisateur;

public class AffectationsTacheDto {
	private AffectationUtilisateur affectation;
	private Utilisateur ressources;
	
	
	
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
	
	
}
