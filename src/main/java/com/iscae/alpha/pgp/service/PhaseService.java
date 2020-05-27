package com.iscae.alpha.pgp.service;

import java.util.List;

import com.iscae.alpha.pgp.entities.Phase;

public interface PhaseService {
	
	Phase addPhase(Phase phase);
	void deleatePhase(Long phaseId);
	Phase updatePhaseE(Phase phase);
	Phase findPHhaseById(Long phaseId);
	List<Phase> findAllPhase();
	
}
