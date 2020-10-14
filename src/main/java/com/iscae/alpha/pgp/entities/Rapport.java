package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rapport implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id_rapport")
	private Long idRapport;
	@Column(name="name_rapport")
	private String nameRapport;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_ajout")
	private Date dateAjout;
	private String description;
	
	@ManyToOne
	private Utilisateur user;

	public Rapport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rapport(String nameRapport, Date dateAjout, String description, Utilisateur user) {
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

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	
	
}
