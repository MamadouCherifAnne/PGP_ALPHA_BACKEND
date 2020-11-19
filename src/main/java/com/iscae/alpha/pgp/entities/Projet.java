package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="num_projet")
	private Long numProjet;
	@Column(name="nom_projet")
	private String nomProjet;
	

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="debut_projet")
	private Date debutProjet;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fin_projet")
	private Date finProjet;
	
	@Column(name="zone_realisation")
	private String zoneRealisation;
	
	// la relation entre projet et utilisateurs 
	 @ManyToOne
	 @JsonBackReference(value="responsable-projet")
	 @JoinColumn(name ="responsable_id_user")
	 private Utilisateur responsable;
	
	 //L' entreprise dont appartient ce projet
	 @JsonBackReference(value="entreprise-projet")
	 @ManyToOne
	 @JoinColumn(name="entreprise_id_entreprise")
	 private Entreprise entreprise;
	 
	 
	 @JsonManagedReference(value="risque-projet")
	 @OneToMany(mappedBy = "projet")
	 private List<Risque> risques;
	 
	 @JsonManagedReference(value="phase-projet")
	 @OneToMany(mappedBy = "projet")
	 private List<Phase> phases;
	 
	 // COmmentaire
	 @JsonIgnore
	 @OneToMany(mappedBy = "projetComment",cascade = CascadeType.ALL)
	 private List<Commentaire> commentaires;

	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Projet(String nomProjet, String description, Date debutProjet, Date finProjet, String zoneRealisation,
			Utilisateur responsable, Entreprise entreprise, List<Risque> risques, List<Phase> phases,
			List<Commentaire> commentaires) {
		super();
		this.nomProjet = nomProjet;
		this.description = description;
		this.debutProjet = debutProjet;
		this.finProjet = finProjet;
		this.zoneRealisation = zoneRealisation;
		this.responsable = responsable;
		this.entreprise = entreprise;
		this.risques = risques;
		this.phases = phases;
		this.commentaires=commentaires;
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



	public Utilisateur getResponsable() {
		return responsable;
	}



	public void setResponsable(Utilisateur responsable) {
		this.responsable = responsable;
	}



	public Entreprise getEntreprise() {
		return entreprise;
	}



	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
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



	public List<Commentaire> getCommentaires() {
		return commentaires;
	}



	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	

	 
}
