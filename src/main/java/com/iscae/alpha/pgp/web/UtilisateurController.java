package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.UtilisateurService;
import com.iscae.alpha.pgp.service.UtilisateurServiceImplementation;



@RestController
@RequestMapping("/utilisateur")

public class UtilisateurController {
	
	//
	@Autowired
	UtilisateurServiceImplementation userService;
	
	@GetMapping("/all")
	public List<Utilisateur> getALlUser() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		boolean deleteResult=userService.deleteUser(id);
		if(deleteResult==true) {
		return "SUCCESS";
	}else {
		return "La suppression a echoué";
		}
	}
	
	@GetMapping("/find/{nom}")
	public Utilisateur getUserByName(@PathVariable String prenom){
		
		return userService.getUserByName(prenom);
		
	}
	
	@PostMapping("/new")
	public Utilisateur addUser(@RequestBody Utilisateur user) {
		
		return userService.addUser(user);
		
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable Long id,@RequestBody Utilisateur user) {
		try {
		user.setIdUser(id);
		userService.updateUser(user);
		return "Succes";
		}catch(Exception e) {
			return e.getMessage()+"La modification n'a pa spu être effectués";
			
		}
	}

}
