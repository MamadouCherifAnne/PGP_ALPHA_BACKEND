package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController

public class UtilisateurController {
	
	@GetMapping("/utilisateur")
	public String bonsoirkk() {
		return "Bonsoir cher KAEDIEN";
	}
	
	@GetMapping("/pgp")
	public String Pgp() {
		return "Bonsoir ici cest pour les utilisateurs seuls";
	}

}
