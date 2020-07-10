package com.iscae.alpha.pgp.web;

import java.util.Date;
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

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.service.ProjetServiceImp;

@RestController
@RequestMapping("/projet")
@CrossOrigin(origins= "*")
public class ProjetController {
	
	@Autowired
	private ProjetServiceImp projetService;
//.................Ajout d'un projet .....................................................................
//................................................................................................
	@PostMapping("/add")
	public Projet AddProjet(@RequestBody Projet projet ) {
		
		return projetService.AddProjet(projet);
		
	}
	
	//.................update d'un projet .....................................................................
	//................................................................................................
	@PostMapping("/update/{id}")
	public String updateProjet(@PathVariable Long id,@RequestBody Projet projet) {
		try {
			projet.setNumProjet(id);
			projetService.updateProjet(projet);
			
			return "Success";
		}catch(Exception e) {	
			return e.getMessage() + "Updating project Error";
		}			
	}
	
	//.................delate d'un projet .....................................................................
	//................................................................................................
	@DeleteMapping("delete/{id}")
	public String deleteProjet(@PathVariable Long id) {
		try {
		projetService.deleateProjet(id);
		return "Success";
		}catch(Exception e) {
			return e.getMessage() + "Deleation error rien à supprimer";
		}
		
	}
	
	//.................rechercher tous les projet.....................................................
	//................................................................................................
	@GetMapping("/findAll")      
	public List<Projet> findAllProjet(){
		
		return projetService.findAllProjets();
	}
	
	//.................rechercher par date de debut  d'un projet .......................................
	//................................................................................................
	@GetMapping("/findByDateDebut")
	public Projet findByDatedebut(Date dateDebut) {
		Projet projet1 = projetService.findByDateDebut(dateDebut);
		return projet1;
	}
	
	//.................rechercher par date de fin d'un projet .........................................
	//................................................................................................
	@GetMapping("/findByDateFin")
	public Projet findDateFin(Date dateFin) {
		Projet projet = projetService.findByDateFin(dateFin);
		return projet;
	}
	
	@GetMapping("/findById/{id}")
	public Projet findById(@PathVariable("id") Long projetId) {
		return projetService.findProjetById(projetId);
	}
	
	@GetMapping("/AllphaseDeProjet/{idProjet}")
	public List<Phase> AllPhaseProjet(@PathVariable Long idProjet){
		return projetService.listPhaseProjet(idProjet);
	}
	
	// Toutes les Taches d'un projet
	@GetMapping("/projectAllTask/{idProjet}")
	public List<Tache> allTaskForThisProject(@PathVariable Long idProjet){
		return projetService.projectTasks(idProjet);
	}

}
