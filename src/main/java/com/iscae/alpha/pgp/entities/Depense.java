package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Depense implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="num_depense")
	private Long NumDepense;
	private String libelle;
	@Column(name="cout_depense")
	private double coutDepense;
	private String description;
	@Column(name="date_enregistrement")
	private Date dateEnregistrement;
	
	@JsonBackReference(value="depense-tache")
	@ManyToOne
	@JoinColumn(name="tache_num_tache")
	private Tache tache;

	public Depense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Depense(String libelle, double coutDepense, String description, Date dateEnregistrement,Tache tache) {
		super();
		this.libelle = libelle;
		this.coutDepense = coutDepense;
		this.description = description;
		this.tache = tache;
		this.dateEnregistrement=dateEnregistrement;
	}

	// GETTERS AND SETTERS
	
	public String getLibelle() {
		return libelle;
	}

	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
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

	public Long getNumDepense() {
		return NumDepense;
	}

	public void setNumDepense(Long numDepense) {
		this.NumDepense = numDepense;
	}
	
	
	

}
