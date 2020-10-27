package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.entities.Projet;

public interface RapportProjectservice {
	
	public List<Map<String, Object>> reportProjet(Long projetId );

	public List<Map<String, Object>> rapportPhase(Long phaseId);
}
