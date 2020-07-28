package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long idRole;
	private String role;
	
	@JsonBackReference
	@OneToMany(mappedBy = "role", fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Utilisateur> users;


	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String role, List<Utilisateur> users) {
		super();
		this.role = role;
		this.users = users;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}
	
	
}
