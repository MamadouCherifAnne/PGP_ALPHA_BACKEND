package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.entities.Role;

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
		//Verifier si l'utilisateur existe puis changer les valuers a modifier
		Optional<Role> oldRole= roleRepo.findById(role.getIdRole());
		if(oldRole!=null && roleRepo.findByRole(oldRole.get().getRole())==null) {
		// Modifier les champs a modifier
			oldRole.get().setRole(role.getRole());;
			}
		
		return roleRepo.save(oldRole.get());
	}

	@Override
	public String deleteRole(Long id) {
		// TODO Auto-generated method stub
		try {
			roleRepo.delete(roleRepo.findById(id).get());
			return "SUCCESS";
		}catch(Exception e) {
		return e.getMessage()+"La suppresion a echoue verifier la requete";
		}
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

}
