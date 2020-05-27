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

	public Profession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profession(String titreProfession, List<Utilisateur> utilisateurs) {
		super();
		this.titreProfession = titreProfession;
		this.utilisateurs = utilisateurs;
	}

	public Long getNumProfession() {
		return NumProfession;
	}

	public void setNumProfession(Long numProfession) {
		NumProfession = numProfession;
	}

	public String getTitreProfession() {
		return titreProfession;
	}

	public void setTitreProfession(String titreProfession) {
		this.titreProfession = titreProfession;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	

}
