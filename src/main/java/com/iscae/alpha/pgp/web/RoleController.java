package com.iscae.alpha.pgp.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")

@CrossOrigin(origins = "http://localhost:4200")


public class RoleController {
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	

}
