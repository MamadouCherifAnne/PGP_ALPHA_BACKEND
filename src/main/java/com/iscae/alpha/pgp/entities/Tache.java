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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Tache implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numTache;

	private String nomTache;
	private String description;
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
	
	
	@JsonProperty("predecesseurs")
	@OneToMany(cascade= CascadeType.MERGE ,fetch = FetchType.LAZY)
	private List<Tache> predecesseurs;
	
	//relatives aux  sous taches
	
	
	@JsonProperty("les_sous_taches")
	@OneToMany(cascade= CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<Tache>  les_sous_taches;  
	

	public List<Tache> getPredecesseurs() {
		return predecesseurs;
	}


	public void setPredecesseurs(List<Tache> predecesseurs) {
		this.predecesseurs = predecesseurs;
	}


	public List<Tache> getLes_sous_taches() {
		return les_sous_taches;
	}


	public void setLes_sous_taches(List<Tache> les_sous_taches) {
		this.les_sous_taches = les_sous_taches;
	}


	public Tache() {
		super();
		// TODO Auto-generated constructor stub
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
	public Long getNumTache() {
		return numTache;

	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public void setNumTache(Long numTache) {
		this.numTache = numTache;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}





	
	
	

}
