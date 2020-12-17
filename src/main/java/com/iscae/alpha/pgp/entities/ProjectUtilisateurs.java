package com.iscae.alpha.pgp.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project_membres")
public class ProjectUtilisateurs {

	
	@EmbeddedId
	private ProjectUserID idMembre;
	private String role;
	public ProjectUtilisateurs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectUtilisateurs(ProjectUserID idMembre, String role) {
		super();
		this.idMembre = idMembre;
		this.role = role;
	}
	public ProjectUserID getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(ProjectUserID idMembre) {
		this.idMembre = idMembre;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
