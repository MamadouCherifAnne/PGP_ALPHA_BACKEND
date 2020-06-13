package com.iscae.alpha.pgp.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Role;
import com.iscae.alpha.pgp.service.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	@PostMapping("/new")
	public Role addNewRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}
	//la modification d'un role
	@PostMapping("/update/{id}")
	public String updateRole(@PathVariable Long id, @RequestBody Role role) {
		role.setIdRole(id);
		try {
				roleService.updateRole(role);
				LOGGER.debug("La suppression est bonne");
				return "Modification effectué avec succes";
		}
		catch(Exception updateException) {
			LOGGER.debug("Queleques problemes rencontre au cours de la supprssion");
			return " La modification  a echoué";
		}
	}
	
	@PostMapping("/delete/{id}")
	public String deleteRole(@PathVariable Long id) {
		
		LOGGER.debug("Suppresion d'un role ");
		return roleService.deleteRole(id);
	}
	
	@GetMapping("/all")
	public List<Role> getAllRoles(){
		return roleService.getAll();
	}
	

}
