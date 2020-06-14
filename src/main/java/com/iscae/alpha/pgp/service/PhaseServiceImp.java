package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.PhaseRepository;
import com.iscae.alpha.pgp.entities.Phase;
@Service 
public class PhaseServiceImp implements PhaseService{
	
	@Autowired
	private PhaseRepository phaseRepository;
	
//.................Ajout phase........................................................................
//....................................................................................................
	@Override
	public Phase addPhase(Phase phase) {
		
		return phaseRepository.save(phase);
	}

	//.................deleate phase........................................................................
	//....................................................................................................
	@Override
	public void deleatePhase(Long phaseId) {
		Phase phase = phaseRepository.findById(phaseId).get();
		phaseRepository.delete(phase);	
	}
	
	//.................update phase........................................................................
	//....................................................................................................
	@Override
	public boolean  updatePhase(Phase phase) {
		Optional<Phase> phase1 = phaseRepository.findById(phase.getNumPhase());
		if(phase1 != null) {
		Phase phase2 = phase1.get();
		phase2.setNumPhase(phase.getNumPhase());
		phase2.setTitrePhase(phase.getTitrePhase());
		phase2.setTache(phase.getTache());
		phase2.setProjet(phase.getProjet());
		phaseRepository.save(phase2);
		return true;
		}
		return false;
	}
	
	//.................rechercher par 'l'id de la phase........................................................................
	//....................................................................................................
	@Override
	public Phase findPHhaseById(Long phaseId) {
		Phase phase = phaseRepository.findById(phaseId).get();
		return phase;
	}

	//.................rechercher toutes les phases........................................................................
	//....................................................................................................
	@Override
	public List<Phase> findAllPhase() {
		return phaseRepository.findAll();
	}
	

}