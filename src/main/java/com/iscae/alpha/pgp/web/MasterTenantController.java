package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iscae.alpha.pgp.ConstantWebApi;
import com.iscae.alpha.pgp.dto.TenantCompteDto;
import com.iscae.alpha.pgp.dto.UtilisateurDto;
import com.iscae.alpha.pgp.entities.DataSourceConfig;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.MasterTenantService;


@RestController
@RequestMapping("/locataire")
@CrossOrigin(origins="*")
public class MasterTenantController {
	@Autowired
	MasterTenantService masterTenantservice;
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String urlToAlfaSecurityApp = "utilisateur/";
	
	@PostMapping(value = "/newLocataire")
	public int  addNewTenant(@RequestBody TenantCompteDto compte) {
		DataSourceConfig tenantDS = compte.getTenantCompte();
		//DataSourceConfig ds = null;
	
		if(masterTenantservice.getByTenantId(compte.getProprietaire().getCompany()) != null) {
			return 0;
			
		}else {
			Utilisateur user   = compte.getProprietaire();
			// preparer l'ajout simultane dans l'api de gestion de l'authentification"
			UtilisateurDto userDto = new UtilisateurDto();
			userDto.setUsername(user.getUsername());
			userDto.setNom(user.getNom());
			userDto.setPrenom(user.getPrenom());
			userDto.setActif(false);
			userDto.setTelephone(user.getTelephone());
			userDto.setEmail(user.getEmail());
			userDto.setAdresse(user.getAdresse());
			userDto.setCompany(user.getCompany());
			userDto.setPassword(user.getPassword());
			
			String service ="new";
			String url = ConstantWebApi.urlToSecurityApp + urlToAlfaSecurityApp + service;
			final Utilisateur responseBody = restTemplate.postForObject(url, userDto, Utilisateur.class);
			if(responseBody!=null) {
					// creer lespace et l4utilisqteur
				//masterTenantservice.addNewLocataire(tenantDS.getName());
				return 1;
			}else {
				return -1;
			}
		}
	}
	
	// add proprietaire
	
	
	
}
