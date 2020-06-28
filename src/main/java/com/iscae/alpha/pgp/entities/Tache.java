package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tache implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long NumTache;
	private String nomTache;
	@Temporal(TemporalType.DATE)
	private Date debutTache;
	@Temporal(TemporalType.DATE)
	private Date finTache;
	private double tauxAvancement;
	private double chargeTache;
	private String niveauPriorite;
	private int duree;
	
	@ManyToOne()
	private Phase phase;
	
	@OneToOne
	private Facture facture;
	
	@OneToMany(mappedBy = "tache" )
	private List<Depense> depenses;
	
	@OneToMany(mappedBy = "tacheConcerne")
	private List<Fichier> fichiers;
	
	

	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Tache(String nomTache, Date debutTache, Date finTache, double tauxAvancement, double chargeTache,
			String niveauPriorite, Phase phase, Facture facture, List<Depense> depenses, 
			List<Fichier> fichiers,  int duree) {
		super();
		this.nomTache = nomTache;
		this.debutTache = debutTache;
		this.finTache = finTache;
		this.tauxAvancement = tauxAvancement;
		this.chargeTache = chargeTache;
		this.niveauPriorite = niveauPriorite;
		this.phase = phase;
		this.facture = facture;
		this.depenses = depenses;
		this.fichiers = fichiers;
		this.duree = duree;
	}

	

	public Long getNumTache() {
		return NumTache;
	}



	public void setNumTache(Long numTache) {
		NumTache = numTache;
	}



	public String getNomTache() {
		return nomTache;
	}



	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}



	public Date getDebutTache() {
		return debutTache;
	}



	public void setDebutTache(Date debutTache) {
		this.debutTache = debutTache;
	}



	public Date getFinTache() {
		return finTache;
	}



	public void setFinTache(Date finTache) {
		this.finTache = finTache;
	}



	public double getTauxAvancement() {
		return tauxAvancement;
	}



	public void setTauxAvancement(double tauxAvancement) {
		this.tauxAvancement = tauxAvancement;
	}



	public double getChargeTache() {
		return chargeTache;
	}



	public void setChargeTache(double chargeTache) {
		this.chargeTache = chargeTache;
	}



	public String getNiveauPriorite() {
		return niveauPriorite;
	}



	public void setNiveauPriorite(String niveauPriorite) {
		this.niveauPriorite = niveauPriorite;
	}



	public Phase getPhase() {
		return phase;
	}



	public void setPhase(Phase phase) {
		this.phase = phase;
	}



	public Facture getFacture() {
		return facture;
	}



	public void setFacture(Facture facture) {
		this.facture = facture;
	}



	public List<Depense> getDepenses() {
		return depenses;
	}



	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}



	public List<Fichier> getFichiers() {
		return fichiers;
	}



	public void setFichiers(List<Fichier> fichiers) {
		this.fichiers = fichiers;
	}



	public int getDuree() {
		return duree;
	}



	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	
	
	

}
