package com.iscae.alpha.pgp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.entities.Profession;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.UtilisateurService;



@RestController
@RequestMapping("/utilisateur")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
	
	//
	@Autowired
	UtilisateurService userService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	ProfessionRepository profRepo;;
	
	@GetMapping("/all")
	public List<Utilisateur> getALlUser() {
		return userService.getAllUsers();
	}
	
	@PostMapping(value="/delete/{id}", consumes="application/json", produces="application/json")
	public boolean deleteUser(@PathVariable Long id) {
		boolean deleteResult=false;
		
		 deleteResult=userService.deleteUser(id);
		if(deleteResult==true) {
		return true;
	}else {
		return false;
		}
	}
	
	@GetMapping("/find/{nom}")
	public Utilisateur getUserByName(@PathVariable String prenom){
		
		return userService.getUserByName(prenom);
		
	}
	
	@GetMapping("/findUser/{id}")
	public Utilisateur getUserById(@PathVariable Long id){
		
		return userService.getUserById(id);
		
	}
	
	@PostMapping(value="/new", consumes={"application/json"})
	public String addUser(@RequestBody Utilisateur user) {
		
		Utilisateur us1=userService.addUser(user);
		if(us1 != null) {
		return "Successfuly";}
	else {
		return "Ajout a echoue";
	}
		
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
