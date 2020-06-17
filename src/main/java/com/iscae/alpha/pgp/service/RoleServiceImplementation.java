package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.entities.Role;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service
public class RoleServiceImplementation implements  RoleService {
	@Autowired
	RoleRepository roleRepo;

	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepo.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		Role oldRole= roleRepo.getOne(role.getIdRole());
		
		// Set new Values
		if(oldRole!=null) {
			oldRole.setRole(role.getRole());
		}
		
		return roleRepo.save(oldRole);
	}

	@Override
	public boolean deleteRole(Long id) {
		// TODO Auto-generated method stub
		boolean message =false;
		Role role=new Role();
		Optional<Role> verifRole = roleRepo.findById(id);
		if(verifRole.isPresent()) {
			role =verifRole.get();
			if(role.getUsers().isEmpty()) {
				roleRepo.deleteById(id);
				message = true;
			}
		}
		
		return message;
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

}
