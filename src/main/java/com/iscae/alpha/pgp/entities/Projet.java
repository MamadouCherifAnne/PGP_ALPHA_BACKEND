package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue
	private Long numProjet;
	
	private String nomProjet;
	

	private String description;

	@Temporal(TemporalType.DATE)
	private Date debutProjet;
	
	@Temporal(TemporalType.DATE)
	private Date finProjet;
	
	private String zoneRealisation;
	
	// Jai eu des doutes concernant la relation entre projet et utilisateurs 
	 @OneToMany(mappedBy = "projet")

	private List<Utilisateur> responsables;
	 
	 //L' entreprise dont appartient ce projet
	 @JsonBackReference
	 @ManyToOne
	 private Entreprise workSpace;
	 
	 
	 @JsonManagedReference
	 @OneToMany(mappedBy = "projet")
	 private List<Risque> risques;
	 
	 @JsonManagedReference
	 @OneToMany(mappedBy = "projet")
	 private List<Phase> phases;

	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Projet(String nomProjet, String description, Date debutProjet, Date finProjet, String zoneRealisation,
			List<Utilisateur> responsables, Entreprise workSpace, List<Risque> risques, List<Phase> phases) {
		super();
		this.nomProjet = nomProjet;
		this.description = description;
		this.debutProjet = debutProjet;
		this.finProjet = finProjet;
		this.zoneRealisation = zoneRealisation;
		this.responsables = responsables;
		this.workSpace = workSpace;
		this.risques = risques;
		this.phases = phases;
	}



	public Long getNumProjet() {
		return numProjet;
	}



	public void setNumProjet(Long numProjet) {
		this.numProjet = numProjet;
	}



	public String getNomProjet() {
		return nomProjet;
	}



	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getDebutProjet() {
		return debutProjet;
	}



	public void setDebutProjet(Date debutProjet) {
		this.debutProjet = debutProjet;
	}



	public Date getFinProjet() {
		return finProjet;
	}



	public void setFinProjet(Date finProjet) {
		this.finProjet = finProjet;
	}



	public String getZoneRealisation() {
		return zoneRealisation;
	}



	public void setZoneRealisation(String zoneRealisation) {
		this.zoneRealisation = zoneRealisation;
	}



	public List<Utilisateur> getResponsables() {
		return responsables;
	}



	public void setResponsables(List<Utilisateur> responsables) {
		this.responsables = responsables;
	}



	public Entreprise getWorkSpace() {
		return workSpace;
	}



	public void setWorkSpace(Entreprise workSpace) {
		this.workSpace = workSpace;
	}



	public List<Risque> getRisques() {
		return risques;
	}



	public void setRisques(List<Risque> risques) {
		this.risques = risques;
	}



	public List<Phase> getPhases() {
		return phases;
	}



	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}



	 
}
