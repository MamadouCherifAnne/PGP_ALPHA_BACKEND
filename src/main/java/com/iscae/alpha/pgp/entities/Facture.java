package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Facture implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="num_facture")		
	private Long NumFacture;
	@Column(name="code_facture")
	private String codeFacture;
	@Column(name="montant_facture")
	private double montantFacture;
	@Column(name="date_edition")
	private Date dateEdition;
	
	@JsonBackReference(value="facture-tache")
	@OneToOne
	@JoinColumn(name = "tache_num_tache")
	private Tache tache;

	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Facture(String codeFacture, double montantFacture, Tache tache, Date dateEdition) {
		super();
		this.codeFacture = codeFacture;
		this.montantFacture = montantFacture;
		this.tache = tache;
		this.dateEdition=dateEdition;
		
	}

	public Long getNumFacture() {
		return NumFacture;
	}

	public void setNumFacture(Long numFacture) {
		this.NumFacture = numFacture;
	}

	public String getCodeFacture() {
		return codeFacture;
	}

	public void setCodeFacture(String codeFacture) {
		this.codeFacture = codeFacture;
	}

	public double getMontantFacture() {
		return montantFacture;
	}

	public void setMontantFacture(double montantFacture) {
		this.montantFacture = montantFacture;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public Date getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}
	
	
	
	
}
