package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.service.EntrepriseService;

@RestController
@RequestMapping("/entreprise")

@CrossOrigin(origins = "http://localhost:4200")

public class EntrepriseController {

	@Autowired
	EntrepriseService entrepriseService;
	
	// Ajout d'une espace de travail pour gerer les projets
	@PostMapping(value="/addEspaceTravail")
	public Entreprise addEspaceDeTravail(@RequestBody Entreprise entreprise){
		
		return entrepriseService.addEntreprise(entreprise);
	}
	
	// ENtreprise modification
	@PostMapping(value="/update")
	public boolean updateEntreprise(@RequestBody Entreprise entreprise) {
		Entreprise ep = entrepriseService.updateEntreprise(entreprise);
		if(ep != null) {
			return true;
		}
		//  Au cas ou il ya le  meme nom ou un probleme
		return false;
	}
	
	// Suppresion de tache
	@DeleteMapping(value="/delete/{idEntreprise}")
	public boolean deleteEntreprise(@PathVariable Long idEntreprise) {
		if(idEntreprise != null) {
		return entrepriseService.deleteEntreprise(idEntreprise);
		}
		return false;
	}
	
	// Afficher les entreprises
	@GetMapping(value="/all")
	public List<Entreprise> getAllEntreprises(){
		return entrepriseService.findAllEntreprise();
	}
	
	// Get ENtreprise by id
	@GetMapping(value="/findEntreprise/{idEntreprise}")
	public Entreprise getEntrepriseById(@PathVariable Long idEntreprise) {
		return entrepriseService.findEntrepriseById(idEntreprise);
	}
	
	// Get Projects of an entreprise
	@GetMapping(value="/entrepriseProjects/{idEntreprise}")
	public List<Projet> getEntrepriseProjects(@PathVariable Long idEntreprise) {
		return entrepriseService.getEntrepriseProjects(idEntreprise);
	}
}
