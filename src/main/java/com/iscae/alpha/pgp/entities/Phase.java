package com.iscae.alpha.pgp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Phase {
	@Id
	@GeneratedValue
	private Long NumPhase;
	private String titrePhase;
	private String description;
	
	@ManyToOne
	private Projet projet;
	
	@OneToMany(mappedBy = "phase",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Tache> taches;

	public Phase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phase(String titrePhase, String description, Projet projet, List<Tache> tache) {
		super();
		this.titrePhase = titrePhase;
		this.description = description;
		this.projet = projet;
		this.taches = tache;
	}

	public Long getNumPhase() {
		return NumPhase;
	}

	public void setNumPhase(Long numPhase) {
		NumPhase = numPhase;
	}

	public String getTitrePhase() {
		return titrePhase;
	}

	public void setTitrePhase(String titrePhase) {
		this.titrePhase = titrePhase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public List<Tache> getTache() {
		return taches;
	}

	public void setTache(List<Tache> tache) {
		this.taches = tache;
	}

	
}
