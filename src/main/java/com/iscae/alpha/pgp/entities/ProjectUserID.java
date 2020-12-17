package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectUserID implements Serializable {
	@Column(name="id_user")
	private Long idUser;
	@Column(name="id_projet")
	private Long idProjet;
	public ProjectUserID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ProjectUserID(Long idUser, Long idProjet) {
		super();
		this.idUser = idUser;
		this.idProjet = idProjet;
	}



	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	
	
	
	
}
