package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iscae.alpha.pgp.ConstantWebApi;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.AuthResponse;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/authenticate")
public class AuthentificationController {
	
	@Autowired
	RestTemplate restTemplate;
	
	// Utilisation du service login de l'api alfa gerant l'authentification
	
	@PostMapping(value="/login", consumes = {"application/json"})
	public AuthResponse authentification(@RequestBody Utilisateur user) {
		//mode production : String url = "http://localhost:8080/alfapgpsecurityapp/authenticate/login";
		String url = ConstantWebApi.urlToSecurityApp+"authenticate/login";
		//String url = "http://localhost:8090/authenticate/login";
		System.out.println("Les DONNEES DE USER CONNECTED ####"+ user.getUsername()+" %%%"+user.getPassword());
		HttpHeaders headers=new HttpHeaders();
		//HttpEntity<String> entity=new HttpEntity<String>(headers);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(user, headers);
		final ResponseEntity<AuthResponse> responseBody = restTemplate.exchange(url, HttpMethod.POST,httpEntity, AuthResponse.class);
		/*restTemplate.postForObject(url, user, AuthResponse.class);*/
		
		return responseBody.getBody();
		
		
	}

}
