package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Utilisateur implements Serializable {
	
	@Id
	@GeneratedValue
	private int idUser;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String telephone;
	@ManyToOne
	private Role role;
	@ManyToMany
	private List<Profession> professions;

	
}
