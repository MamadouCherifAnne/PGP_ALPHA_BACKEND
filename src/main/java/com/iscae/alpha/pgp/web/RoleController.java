package com.iscae.alpha.pgp.web;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iscae.alpha.pgp.ConstantWebApi;
import com.iscae.alpha.pgp.dto.Role;

@RestController
@RequestMapping("/role")

@CrossOrigin(origins = "*")


public class RoleController {
	
	@Autowired
	RestTemplate restTemplate;
	
	private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	private static final String urlToAlfaSecurityApp = "role/";
	
	@GetMapping("/all")
	public List<Role> getALlUser() {
		String service ="all";
		String url =ConstantWebApi.urlToSecurityApp+ urlToAlfaSecurityApp+service;
		final ResponseEntity<List<Role>> responseBody = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Role>>() {});
		return responseBody.getBody();
	}
	
	

}
