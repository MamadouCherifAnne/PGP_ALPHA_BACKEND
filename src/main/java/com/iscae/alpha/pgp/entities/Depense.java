package com.iscae.alpha.pgp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Depense {
	@Id
	@GeneratedValue
	private Long NumDepense;
	private String libelle;
	private double coutDepense;
	private String description;
	
	@ManyToOne()
	private Tache tache;

	public Depense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Depense(String libelle, double coutDepense, String description, Tache tache) {
		super();
		this.libelle = libelle;
		this.coutDepense = coutDepense;
		this.description = description;
		this.tache = tache;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getCoutDepense() {
		return coutDepense;
	}

	public void setCoutDepense(double coutDepense) {
		this.coutDepense = coutDepense;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}
	
	
	

}
