package com.iscae.alpha.pgp.service;


import java.util.List;


import com.iscae.alpha.pgp.entities.Role;

public interface RoleService {
	
	// Ajout de role
	public Role addRole(Role role);
	//Modification de role
	public Role updateRole(Role role);

	// Suppression de role
	public boolean deleteRole(Long id);
	 //Afficher tout les roles
	public List<Role> getAll();



}
