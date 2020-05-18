package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class Entreprise implements Serializable{
	private Long idEntreprise;
	private String nameEntreprise;
	private String domaine_Entreprise;
	private String adresse;
	
	@OneToMany(mappedBy = "rapport", fetch  = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Utilisateur> users;

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String nameEntreprise, String domaine_Entreprise, String adresse, List<Utilisateur> users) {
		super();
		this.nameEntreprise = nameEntreprise;
		this.domaine_Entreprise = domaine_Entreprise;
		this.adresse = adresse;
		this.users = users;
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

	public List<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}
	
	 
}
