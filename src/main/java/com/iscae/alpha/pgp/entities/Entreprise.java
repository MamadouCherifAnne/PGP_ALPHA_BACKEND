package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Entreprise implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id_entreprise")
	private Long idEntreprise;
	@Column(name="name_entreprise")
	private String nameEntreprise;
	
	@Column(name="domaine_entreprise")
	private String domaine_Entreprise;
	private String adresse;
	
	@JsonBackReference(value="user-entreprise")
	@OneToMany(mappedBy="entreprise", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	
	private List<Utilisateur> users;
	
	// Les projets d' une entreprise
	@JsonManagedReference(value="entreprise-projet")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="workSpace")
	private List<Projet> projets;
	
	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Entreprise(String nameEntreprise, String domaine_Entreprise, String adresse,List <Utilisateur> users,
			List<Projet> projets) {
		super();
		this.nameEntreprise = nameEntreprise;
		this.domaine_Entreprise = domaine_Entreprise;
		this.adresse = adresse;
		this.users = users;
		this.projets = projets;
	}
	
	


	public List<Projet> getProjets() {
		return projets;
	}


	public void setProjets(List<Projet> projets) {
		this.projets = projets;
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

	public List <Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}

	
	 
}
