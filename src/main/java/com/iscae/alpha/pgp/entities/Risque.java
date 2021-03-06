package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Risque implements Serializable {
	@Id
	@GeneratedValue
	@Column(name ="num_risque")
	private Long numRisque;
	@Column(name ="probablite_risque")
	private int probabliteRisque;
	@Column(name ="taux_impact")
	private int tauxImpact;

	@Column(name ="domaine_impacte")
	private  String  domaineImpacte;

	private String etat;
	private String source;
	
	@JsonBackReference(value="risque-projet")
	@ManyToOne
	@JoinColumn(name ="projet_num_projet")
	private Projet projet;

	public Risque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Risque(int probabliteRisque, int tauxImpact, String domaineImpacte, String etat, String source,
			Projet projet) {
		super();
		this.probabliteRisque = probabliteRisque;
		this.tauxImpact = tauxImpact;
		this.domaineImpacte = domaineImpacte;
		this.etat = etat;
		this.source = source;
		this.projet = projet;
	}

	public int getProbabliteRisque() {
		return probabliteRisque;
	}

	public void setProbabliteRisque(int probabliteRisque) {
		this.probabliteRisque = probabliteRisque;
	}

	public int getTauxImpact() {
		return tauxImpact;
	}

	public void setTauxImpact(int tauxImpact) {
		this.tauxImpact = tauxImpact;
	}

	public String getDomaineImpacte() {
		return domaineImpacte;
	}

	public void setDomaineImpacte(String domaineImpacte) {
		this.domaineImpacte = domaineImpacte;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	
	
	

}
