package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;

import javax.persistence.Column;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Utilisateur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idUser;
	@Column(unique = true)
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String adresse;
	private boolean actif;
	private String telephone;
	
	
	@ManyToOne
	private Role role;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Utilisateur_Profession",
	joinColumns = @JoinColumn(name="id_user"), inverseJoinColumns = @JoinColumn(name="numProfession"))
	@JsonSetter

	private List<Profession> professions;
	
	@ManyToOne
	private Projet projet;
	

	@OneToMany(mappedBy = "user")
	private List<Rapport> rapports;

	@OneToMany(mappedBy = "user", fetch  = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Commentaire> commentaires;
	
	
	// Les entreprises dedier a un utilisateur
	@JsonBackReference
	@ManyToOne
	private Entreprise entreprise;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(String nom, String prenom, String email, String password, String adresse, boolean actif,
			String telephone, Role role, List<Profession> professions, Projet projet, List<Rapport> rapports,
			List<Commentaire> commentaires, Entreprise entreprise) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.actif = actif;
		this.telephone = telephone;
		this.role = role;
		this.professions = professions;
		this.projet = projet;
		this.rapports = rapports;
		this.commentaires = commentaires;
		this.entreprise = entreprise;
	}

	// GETTERS AND SETTERS ......................................................................................................
	
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public List<Rapport> getRapports() {
		return rapports;
	}

	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	
}