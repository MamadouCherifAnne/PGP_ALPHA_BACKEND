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

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.service.PhaseServiceImp;

@RestController
@RequestMapping("/phase")
@CrossOrigin(origins="*")
public class PhaseController {
	
	@Autowired
	private PhaseServiceImp phaseService;
	
	@PostMapping("/add")
	public Phase addPhase(@RequestBody Phase phase) {
		
		System.out.println("############"+phase.getProjet().getNumProjet());
		return  phaseService.addPhase(phase);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleatePhase(@PathVariable Long phaseId) {
		try {
			phaseService.deleatePhase(phaseId);
			return "Success";
		}catch(Exception e) {
			return e.getMessage() + "erreur de suppression"; 
		}
	}
	
	@PostMapping("/update/{phaseId}")
	public String updatePhase(@PathVariable Long phaseId, @RequestBody Phase phase) {
		try {
			phase.setNumTache(phaseId);
			phaseService.updatePhase(phase);
			return "Modifiée avec succes"; 
		}catch(Exception e){
			return e.getMessage() + "erreur de modification"; 
		}
	}
	
	@GetMapping("/allPhase")
	public List<Phase> findAllPhase(){
		return phaseService.findAllPhase();
	}
}
