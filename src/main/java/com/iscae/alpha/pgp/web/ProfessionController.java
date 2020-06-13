package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Profession;
import com.iscae.alpha.pgp.service.ProfessionService;

@RestController
@RequestMapping("/profession")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessionController {
	@Autowired
	ProfessionService profService;
	
	@PostMapping(value="/add", consumes={"application/json"})
	public Profession addProfession(@RequestBody Profession profession){
		return profService.addProfession(profession);
		
	}
	@GetMapping(value="/all")
	public List<Profession> getAllProfessions(){
		return profService.getAllProfession();
	}
	

}
