package com.iscae.alpha.pgp.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.rapport.ReportProjet;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


@Service
public class RapportProjectServiceImp implements RapportProjectservice {
	
	@Autowired 
	private ProjetServiceImp projetService;
	
	private PhaseServiceImp phaseService;

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

	///......................un autre rapprot................................
	@Override
	public List<ReportProjet> rapportProjet1(Long projetId) {
		List<ReportProjet> listeRapport = new ArrayList<>();
		List<Tache> taches = projetService.projectTasks(projetId);
		
		Date now = new Date();	 
		
		
		for(Tache tache: taches) {
				ReportProjet raportProjet = new ReportProjet();
				 //if(tache.getFinTache() != null) {
				 raportProjet.setProjectName(tache.getPhase().getProjet().getNomProjet());
				 raportProjet.setDateDebutProjet(tache.getPhase().getProjet().getDebutProjet());
				 raportProjet.setDateFinProjet(tache.getPhase().getProjet().getFinProjet());
				 raportProjet.setPhaseName(tache.getPhase().getNomTache());
				 if(tache.getTauxAvancement() == 100 && isFinished(tache) == 0) { raportProjet.setStatut("Termée");}
				 if(tache.getFinTache().before(now) && tache.getTauxAvancement() != 100) { raportProjet.setStatut("En retard");}
				 if(tache.getDebutTache().before(now) && tache.getFinTache().after(now) &&tache.getTauxAvancement() != 100) {
					 raportProjet.setStatut("En cours");
				 }
				 String avancement = ""+ tache.getTauxAvancement()+" %";

				 raportProjet.setTaskName(tache.getNomTache());
				 raportProjet.setAvancemant(avancement);
				 raportProjet.setDateDebutTask(tache.getDebutTache());
				 raportProjet.setDateFinTask(tache.getFinTache());
				 raportProjet.setPriorite(tache.getNiveauPriorite()); 

				 listeRapport.add(raportProjet); 

			}	

			return listeRapport;
			 
		 }
		
		 
		

	@Override
	public List<Map<String, Object>> rapportPhase(Long phaseId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
