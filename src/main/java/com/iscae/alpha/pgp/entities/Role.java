package com.iscae.alpha.pgp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long idRole;
	private String role;
	
	@OneToMany(mappedBy = "role", fetch  = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Utilisateur> users;
	

}
