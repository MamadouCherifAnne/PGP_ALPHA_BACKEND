package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.AuthResponse;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/authenticate")
public class AuthentificationController {
	
	@Autowired
	RestTemplate restTemplate;
	
	// Utilisation du service login de l'api alfa gerant l'authentification
	
	@PostMapping("/login")
	public AuthResponse authentification(@RequestBody Utilisateur user) {
		String url = "http://localhost:8090/authenticate/login";
		final AuthResponse responseBody = restTemplate.postForObject(url, user, AuthResponse.class);
		return responseBody;
		
		
	}

}
