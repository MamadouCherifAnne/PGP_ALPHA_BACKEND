package com.iscae.alpha.pgp.service;

import com.iscae.alpha.pgp.entities.Role;

public interface RoleService {
	public Role addRole(Role role);
	public Role updateRole(Role role);
	public String deleteRole(Long id);

}
