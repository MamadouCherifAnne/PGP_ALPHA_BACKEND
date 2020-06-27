package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		return tacheService.addTache(tache);
	}
	
	@PostMapping("/update/{tacheId}")
	public String updateTache(Long tacheId,Tache tache) {
		try {
			tache.setNumTache(tacheId);
			tacheService.updateTache(tache);
			return "tache modifier";
		}catch(Exception e) {
			return e.getMessage()+ "La tache n'est pas modifiée";
		}
	}
	
	@PostMapping("/delete/{tacheId}")
	public String deleate(Long tacheId) {
		try {
			tacheService.deleateTache(tacheId);
			return "tache supprimée";
		}catch(Exception e) {
			return e.getMessage()+ "La tache n'est pas supprimée";
		}
	}
}
