package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue
	private Long idRole;
	private String role;
	
	@OneToMany(mappedBy = "role", fetch  = FetchType.EAGER, cascade = CascadeType.ALL)
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
