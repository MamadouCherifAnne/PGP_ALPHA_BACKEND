package com.iscae.alpha.pgp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Profession {
	@Id
	@GeneratedValue
	private Long NumProfession;
	private String titreProfession;
	
	@ManyToMany(mappedBy = "professions")
	private List<Utilisateur> utilisateurs;
	
	

}
