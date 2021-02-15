package com.iscae.alpha.pgp.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.dao.TacheRepository;
import com.iscae.alpha.pgp.dto.InfoTaches;
import com.iscae.alpha.pgp.dto.infoDepenseTache;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Depense;
import com.iscae.alpha.pgp.entities.Facture;
import com.iscae.alpha.pgp.entities.Fichier;
import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.CommentaireService;
import com.iscae.alpha.pgp.service.DepenseService;
import com.iscae.alpha.pgp.service.FactureService;
import com.iscae.alpha.pgp.service.ProjetService;
import com.iscae.alpha.pgp.service.TacheServiceImpl;

@RestController
@RequestMapping("/tache")
@CrossOrigin(origins="*")
public class TacheController {
	
	@Autowired
	private TacheServiceImpl tacheService;
	@Autowired
	TacheRepository tacheRepo;
	@Autowired
	CommentaireService commentService;
	@Autowired
	DepenseService depenseService;
	@Autowired
	FactureService factureService;
	@Autowired
	ProjetService projetService;
	
	
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
	
	
	@PutMapping("/updateSecondaire/{tacheId}")
	public String updateTacheSecondaire(@PathVariable Long tacheId, @RequestBody Tache tache) {
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
	
//  Retourner la liste des potentilles tache precedentes d'une tache
	@GetMapping(value="/potentielPredecesseurs/{idTache}")
	public List<Tache> getPotentielPredecesseurs(@PathVariable Long idTache){
		return tacheService.potentielPredecesseurs(idTache);
	}
	
	// Service d'ajou des commentaires a une tache
	
	@PostMapping(value="/addCommentsToTask", consumes= {"application/json"})
	public Commentaire addComments(@RequestBody Commentaire  comments) {
		return commentService.addComment(comments);
	}
	
	// Afficher les commentaires effectuer sur une tache
	@GetMapping(value="commentsOfTask/{idTache}")
	public List<Commentaire> getCommentOfTask(@PathVariable Long idTache){
		
		return commentService.getCommentsOfTask(idTache);
	}
	
	// Afficher les depense d'une tache
	@GetMapping(value="/depenseOfTask/{idTache}")
	public List<Depense> getDepenseOfTask(@PathVariable Long idTache){
		return tacheService.getDepensesOfTask(idTache);
	}
	
	// Ajouter une depense a une tache
	@PostMapping(value="/addDepenseToTask")
	public Depense addDepenseToTask(@RequestBody Depense depense) {
		return depenseService.addDepense(depense);
	}
	
	//Afficher la liste des depenses en fonction d'une date 
	@GetMapping(value="/depenseOfDate")
	public List<Depense> getDepensesByDate(@RequestBody Date date){
		return depenseService.getDepenseByDate(date);
	}
	//Afficher la facture d'une tache
	@GetMapping(value="/getFacture/{idTache}")
	public Facture getFactureOfTache(@PathVariable Long idTache) {
		return tacheService.getFactureOfTasK(idTache);
	}
	
	// Ajouter une facture a une tache
	@PostMapping(value="/addFactureToTache")
	public Facture addFactureToTask(@RequestBody Facture facture){
		return factureService.addFactureOfTask(facture);
	}
	
	// Supprimer une facture
	@PostMapping(value="/deleteFactureOfTask/{idFacture}")
	public boolean deleteFactureOfTask(@PathVariable Long idFacture){
		return factureService.deleteFacture(idFacture);
	}
	
	// Afficher le cout totale des depense d'une tache
	@GetMapping(value="/costOfTask/{idTache}")
	public double getCostOfTache(@PathVariable Long idTache) {
		
		return (tacheService.getCoutTotaleDepense(idTache)+tacheService.calculCoutRessourcesOfTask(idTache));
	}
	
	@GetMapping(value="/getOwner/{idTache}")
	public String getTheOwner(@PathVariable Long idTache) {
		return tacheService.getTheOwner(idTache);
	}
	

	@GetMapping("/TasksInformation/{projetId}")
	public InfoTaches tasksInformation(@PathVariable Long projetId) {
		return tacheService.TasksInformation(projetId);		
	}
	

	@GetMapping(value="/getTacheProject/{idTache}")
	public Long getTacheProject(@PathVariable Long idTache) {
		return tacheService.getProject(idTache);
	}

	
	@GetMapping(value="/getPhaseDuneTache/{idtache}")
	public Phase getPhaseDuneTache(@PathVariable Long idtache) {
		return tacheService.getPhaseDuneTache(idtache);
	}
	
	@GetMapping(value="/gettachewithdepenses/{idTache}")
	public infoDepenseTache gettacheWithDepenses(@PathVariable Long idTache) {
		return tacheService.getTaskInformationWithDepenses(idTache);
	}
	

	@GetMapping(value="getFiles/{idTache}")
	public List<Fichier> getFiles(@PathVariable Long idTache) {
		return tacheService.getFiles(idTache);
	}

	@DeleteMapping(value="/deleteDepense/{idDepense}")
	public boolean deleteDepenseOfTask(@PathVariable Long idDepense) {
		return this.depenseService.deleteDepense(idDepense);
	}
}
