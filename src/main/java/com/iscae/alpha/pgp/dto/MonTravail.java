package com.iscae.alpha.pgp.dto;

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;

public class MonTravail {
	private Tache tache;
	private Projet projet;
	private Phase phase;
	
	public MonTravail() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public MonTravail(Tache tache, Projet projet, Phase phase) {
		super();
		this.tache = tache;
		this.projet = projet;
		this.phase = phase;
	}


	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	
}
