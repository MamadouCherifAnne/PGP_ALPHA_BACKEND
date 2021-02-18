package com.iscae.alpha.pgp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.rapport.ReportProjet;

public interface RapportProjectservice {
	
	public List<Map<String, Object>> reportProjet(Long projetId );

	public List<Map<String, Object>> reporttacheProjet(Long projetId );
	
	public List<Map<String, Object>> rapportPhase(Long phaseId);

	List<ReportProjet> rapportProjet1(Long projetId);
}
