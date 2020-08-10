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

import com.iscae.alpha.pgp.dao.TacheRepository;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.TacheServiceImpl;

@RestController
@RequestMapping("/tache")
@CrossOrigin(origins="*")
public class TacheController {
	
	@Autowired
	private TacheServiceImpl tacheService;
	@Autowired
	TacheRepository tacheRepo;
	
	
	@PostMapping(value="/add", consumes={"application/json"})
	public Tache ajoutTache(@RequestBody Tache tache) {
		return tacheService.addTache(tache);
	} 
	
	
	@PostMapping("/addJalon")
	public Tache ajoutJalon(@RequestBody Tache tache) {
		
		return tacheService.addJalon(tache);
	}
	
	@PostMapping("/update/{tacheId}")
	public String updateTache(@PathVariable Long tacheId, @RequestBody Tache tache) {
		try {
			tache.setNumTache(tacheId);
			tacheService.updateTache(tache);
			return "tache modifée";
		}catch(Exception e) {
			return e.getMessage() + "erreur de modification";
		}
	}
	
	

	// ALL TASKS
	@GetMapping(value="/all")
	public List<Tache> findAllTask(){
		System.out.println("####################"+ tacheService.findTache(10L).getTachePrecedente());
		return tacheRepo.findAll();
	}

	@DeleteMapping("/delete/{tacheId}")
	public String deleteTahce(@PathVariable Long tacheId) {
		try {
			tacheService.deleateTache(tacheId);
			return "tache supprimé";
		}catch(Exception e)
		{
			return e.getMessage() + "erreur de suppression";
		}
	}
	
	@GetMapping("/findTache/{tacheId}")
	public Tache findTacheById(@PathVariable Long tacheId) {
		return tacheService.findTache(tacheId);
	}

	@GetMapping("/allTache")
	public List<Tache> findAllTache(){
		return tacheService.findAllTache();
	}
	
	// Afficher toutes les ressources d'une tache
	@GetMapping(value="/ressourcesForTache/{idTache}")
	public List<Utilisateur> getRessourcesForThisTache(@PathVariable Long idTache){

		return tacheService.getAllRessources(idTache);
	}
	
	//  Retourner la liste des tache precedentes d'une tache
	@GetMapping(value="/predecesseurs/{idTache}")
	public List<Tache> getPredecesseurs(@PathVariable Long idTache){
		return tacheService.getPredecesseursTask(idTache);
	}
}
