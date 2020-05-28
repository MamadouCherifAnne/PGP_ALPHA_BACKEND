package com.iscae.alpha.pgp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Risque {
	@Id
	@GeneratedValue
	private Long numRisque;
	
	private int probabliteRisque;
	private int tauxImpact;
	
	private  List<String> domaineImpacte;
	private String etat;
	private String source;
	
	@ManyToOne
	private Projet projet;

	public Risque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Risque(int probabliteRisque, int tauxImpact, List<String> domaineImpacte, String etat, String source,
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

	public List<String> getDomaineImpacte() {
		return domaineImpacte;
	}

	public void setDomaineImpacte(List<String> domaineImpacte) {
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