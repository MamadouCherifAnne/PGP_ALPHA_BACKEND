package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rapport implements Serializable{
	@Id
	@GeneratedValue
	private Long idRapport;
	private String nameRapport;
	
	@Temporal(TemporalType.DATE)
	private Date dateAjout;
	private String description;
	
	@OneToMany(mappedBy = "rapport", fetch  = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Utilisateur> user;

	public Rapport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rapport(String nameRapport, Date dateAjout, String description, List<Utilisateur> user) {
		super();
		this.nameRapport = nameRapport;
		this.dateAjout = dateAjout;
		this.description = description;
		this.user = user;
	}

	public Long getIdRapport() {
		return idRapport;
	}

	public void setIdRapport(Long idRapport) {
		this.idRapport = idRapport;
	}

	public String getNameRapport() {
		return nameRapport;
	}

	public void setNameRapport(String nameRapport) {
		this.nameRapport = nameRapport;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Utilisateur> getUser() {
		return user;
	}

	public void setUser(List<Utilisateur> user) {
		this.user = user;
	}
	
	
}
