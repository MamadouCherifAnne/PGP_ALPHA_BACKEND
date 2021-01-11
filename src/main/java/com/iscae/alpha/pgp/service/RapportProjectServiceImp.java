package com.iscae.alpha.pgp.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;

@Service
public class RapportProjectServiceImp implements RapportProjectservice {
	
	@Autowired 
	private ProjetServiceImp projetService;
	
	private PhaseServiceImp phaseService;

	//raport des taches d'un projet 
	@Override
	public List<Map<String, Object>> reportProjet(Long projetId) {
		 Projet p = projetService.findProjetById(projetId);
		 String statut= "";
		 Date now = new Date();	 
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> item = new HashMap<String, Object>();
		 item.put("projectName", p.getNomProjet());
		 item.put("dateDebutProjet", p.getDebutProjet());
		 item.put("dateFinProjet", p.getFinProjet());
		 
		 for(Phase phase: p.getPhases()) {
			 item.put("phaseName", phase.getNomTache());
			 for(Tache tache: phase.getTache()) {
				 if(!(tache.getFinTache()==null)) {
				 //String statut = "demarée";
				 if(tache.getTauxAvancement() == 100 && isFinished(tache) == 0) { statut = "Termée";}
				 if(tache.getFinTache().before(now) && tache.getTauxAvancement() != 100) { statut = "En retard";}
				 if(tache.getDebutTache().before(now) && tache.getFinTache().after(now) &&tache.getTauxAvancement() != 100) {
					 statut = "En cours";
				 }
				 String avancement = ""+ tache.getTauxAvancement()+" %";
				 item.put("taskName", tache.getNomTache());
				 item.put("statut", statut);
				 item.put("avancemant", avancement);
				 item.put("dateDebutTask", tache.getDebutTache());
				 item.put("dateFinTask", tache.getFinTache());
				 item.put("priorite", tache.getNiveauPriorite()); 
			 }
			// result.add(item);
			 }
		 }
		 result.add(item);
		 
		return result;
	}
	
	 public int isFinished(Tache tache){
		    int find = 0;
		    if(tache.getTachePrecedente() != null){
		      for(Tache itache : tache.getTachePrecedente()){
		        if(itache.getTauxAvancement() != 100){
		          find = 1;
		          break;
		        }
		      }
		    }
		    System.out.println("-------------"+find);
		    return find;
		  }

	@Override
	public List<Map<String, Object>> rapportPhase(Long phaseId) {
		 Phase p = phaseService.findPhaseById(phaseId);
		 String statut= "";
		 Date now = new Date();	 
		 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 Map<String, Object> item = new HashMap<String, Object>();
		 item.put("phaseName", p.getNomTache());
		 item.put("projectName", p.getProjet().getNomProjet());
		 item.put("dateDebutProjet", p.getProjet().getDebutProjet());
		 item.put("dateFinProjet", p.getProjet().getFinProjet());
		 for(Tache tache: p.getTache()) {
				 //String statut = "demarée";
				 if(tache.getTauxAvancement() == 100) { statut = "Termée";}
				 if(tache.getFinTache().before(now) && tache.getTauxAvancement() != 100) { statut = "En retard";}
				 if(tache.getDebutTache().before(now) && tache.getFinTache().after(now) &&tache.getTauxAvancement() != 100) {
					 statut = "En cours";
				 
				 String avancement = ""+ tache.getTauxAvancement()+" %";
				 item.put("taskName", tache.getNomTache());
				 item.put("statut", statut);
				 item.put("avancemant", avancement);
				 item.put("dateDebutTask", tache.getDebutTache());
				 item.put("dateFinTask", tache.getFinTache());
				 item.put("priorite", tache.getNiveauPriorite()); 
			 }
			 result.add(item);
			 
		 }
		
		 
		return result;
	}

	
}
