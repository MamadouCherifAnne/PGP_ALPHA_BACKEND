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

import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.service.TacheServiceImpl;

@RestController
@RequestMapping("/tache")
@CrossOrigin(origins="*")
public class TacheController {
	
	@Autowired
	private TacheServiceImpl tacheService;
	
	@PostMapping("/add")
	public Tache ajoutTache(@RequestBody Tache tache) {
		
		System.out.println("##########"+tache.getPredecesseurs());
		return tacheService.addTache(tache);
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
	
	@GetMapping("/findAll")
	public List<Tache> findAllTache(){
		return tacheService.findAllTache();
	}
}
