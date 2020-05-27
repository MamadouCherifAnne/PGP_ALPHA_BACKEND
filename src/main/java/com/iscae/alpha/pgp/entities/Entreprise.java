package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Entreprise implements Serializable{
	@Id
	@GeneratedValue
	private Long idEntreprise;
	private String nameEntreprise;
	private String domaine_Entreprise;
	private String adresse;
	
	@ManyToOne
	private Utilisateur user;

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(Long idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public String getNameEntreprise() {
		return nameEntreprise;
	}

	public void setNameEntreprise(String nameEntreprise) {
		this.nameEntreprise = nameEntreprise;
	}

	public String getDomaine_Entreprise() {
		return domaine_Entreprise;
	}

	public void setDomaine_Entreprise(String domaine_Entreprise) {
		this.domaine_Entreprise = domaine_Entreprise;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	
	 
}
