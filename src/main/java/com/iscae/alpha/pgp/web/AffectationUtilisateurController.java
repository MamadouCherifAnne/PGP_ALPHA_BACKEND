package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.service.AffectationUtilisateurService;

@RestController
@RequestMapping("/affectation")
public class AffectationUtilisateurController {
	@Autowired
	AffectationUtilisateurService userToJobService;
	@PostMapping(value="/add", consumes="application/json")
	public String addAffectationUser(@RequestBody AffectationUtilisateur userForJob) {
		
			userToJobService.addAffectationUser(userForJob);
		 System.out.println("#######+"+ userForJob.getUser_task().getIdUser());
		 return "Added";
	}
	@GetMapping("/all")
	public List<AffectationUtilisateur> getAllAffectation(){
		return userToJobService.getAllAffectations();
	}

}
