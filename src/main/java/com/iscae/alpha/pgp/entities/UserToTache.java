package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserToTache implements Serializable {
	@Column(name="id_user")
	private Long idUser;
	@Column(name="id_tache")
	private Long idTache;
	
	

	public UserToTache() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserToTache(Long idUser, Long idTache) {
		super();
		this.idUser = idUser;
		this.idTache = idTache;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdTache() {
		return idTache;
	}
	public void setIdTache(Long idTache) {
		this.idTache = idTache;
	}

}
