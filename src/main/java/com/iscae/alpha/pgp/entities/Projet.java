package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue
	private Long NumProjet;
	
	private String nomProjet;
	
	@Temporal(TemporalType.DATE)
	private Date debutProjet;
	
	@Temporal(TemporalType.DATE)
	private Date finProjet;
	
	private String zoneRealisation;
	
	// Jai eu des doutes concernant la relation entre projet et utilisateurs 
	 @OneToMany(mappedBy = "projet")
	private List<Utilisateur> responsables;
	
	 @OneToMany(mappedBy = "projet")
	 private List<Risque> risques;
	 
	 @OneToMany(mappedBy = "projet")
	 private List<Phase> phases;

	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projet(String nomProjet, Date debutProjet, Date finProjet, String zoneRealisation,
			List<Utilisateur> responsables, List<Risque> risques, List<Phase> phases) {
		super();
		this.nomProjet = nomProjet;
		this.debutProjet = debutProjet;
		this.finProjet = finProjet;
		this.zoneRealisation = zoneRealisation;
		this.responsables = responsables;
		this.risques = risques;
		this.phases = phases;
	}

	public Long getNumProjet() {
		return NumProjet;
	}

	public void setNumProjet(Long numProjet) {
		NumProjet = numProjet;
	}

	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
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
