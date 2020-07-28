package com.iscae.alpha.pgp.entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Phase  implements Serializable {
	@Id
	@GeneratedValue
	private Long numPhase;
	private String titrePhase;
	private String description;
	
	@JsonBackReference
	@ManyToOne
	private Projet projet;


	@JsonManagedReference
	@OneToMany(mappedBy = "phase")
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
		return numPhase;
	}

	public void setNumPhase(Long numPhase) {
		this.numPhase = numPhase;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
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
