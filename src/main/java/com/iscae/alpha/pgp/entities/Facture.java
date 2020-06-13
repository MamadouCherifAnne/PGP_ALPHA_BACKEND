package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Facture implements Serializable {
	@Id
	@GeneratedValue
	private Long NumFacture;
	private String codeFacture;
	private double montantFacture;
	
	@OneToOne(mappedBy = "facture")
	private Tache tache;

	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Facture(String codeFacture, double montantFacture, Tache tache) {
		super();
		this.codeFacture = codeFacture;
		this.montantFacture = montantFacture;
		this.tache = tache;
	}

	public Long getNumFacture() {
		return NumFacture;
	}

	public void setNumFacture(Long numFacture) {
		NumFacture = numFacture;
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
	
	
}
