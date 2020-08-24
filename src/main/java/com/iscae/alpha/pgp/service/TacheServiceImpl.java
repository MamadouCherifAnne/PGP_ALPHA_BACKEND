package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.TacheRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Depense;
import com.iscae.alpha.pgp.entities.Facture;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;
@Service 
public class TacheServiceImpl implements TacheService{
	
	@Autowired
	private TacheRepository tacheRepository;
	@Autowired
	 AffectationUtilisateurService affectService;
	@Autowired
	UtilisateurService userService;
	@Autowired
	ProjetService projetService;
	@Autowired
	CommentaireService commentService;
	
	
	@Override
	public Tache addTache(Tache tache) {
		return tacheRepository.save(tache);
	}

	@Override
	public Tache updateTache(Tache tache) {
		Optional<Tache> tache1 = tacheRepository.findById(tache.getNumTache());
		if(tache1.isPresent()) {
			Tache tache2 = tache1.get();
			tache2.setNumTache(tache.getNumTache());
			tache2.setNomTache(tache.getNomTache());
			tache2.setDescription(tache.getDescription());
			tache2.setChargeTache(tache.getChargeTache());
			tache2.setDebutTache(tache.getDebutTache());
			tache2.setFinTache(tache.getFinTache());
			tache2.setDuree(tache.getDuree());
			tache2.setDepenses(tache.getDepenses());
			tache2.setDuree(tache.getDuree());
			tache2.setFacture(tache.getFacture());
			tache2.setNiveauPriorite(tache.getNiveauPriorite());
			tache2.setFichiers(tache.getFichiers());
			tache2.setTauxAvancement(tache.getTauxAvancement());
			tache2.setTachePrecedente(tache.getTachePrecedente());
			
			return tacheRepository.save(tache2);
		}else {
			return null;
		}
	
		
	}

	@Override
	public void deleateTache(Long idTache) {
		Tache tache = tacheRepository.findById(idTache).get();
		tacheRepository.delete(tache);
	}

	@Override
	public Tache findTache(Long idTache) {
		if( tacheRepository.findById(idTache).isPresent()) {
		Tache tache = tacheRepository.findById(idTache).get();
		return tache;}
		return null;
	}

	@Override
	public Tache findTahceByDebut(Date dateDebut) {
		Tache tache = tacheRepository.findTahceByDebutTache(dateDebut);
		return tache;
	}

	@Override
	public Tache findTacheByFin(Date dateFin) {
		Tache tache = tacheRepository.findTacheByfinTache(dateFin);
		return tache;
	}

	@Override
	public List<Tache> findAllTache() {
		return tacheRepository.findAll();
	}

	@Override
	public List<Utilisateur> getAllRessources(Long idTache) {
		// TODO Auto-generated method stub
		List<Utilisateur> ressources= new ArrayList<>();
		List<AffectationUtilisateur> affectations = new ArrayList<>();
		affectations = affectService.getAffectationsForTache(idTache);
		if(!affectations.isEmpty()) {
			for(AffectationUtilisateur af:affectations) {
				// On recupere l'identifiant de la tache puis on allimente la liste des tache
				Utilisateur ressource =new Utilisateur();
				ressource =userService.getUserById(af.getUser_task().getIdUser());
				ressources.add(ressource);
			
			}
			return ressources;
		}
		else {
			return null;
		}
	}

	@Override
	public List<Tache> getPredecesseursTask(Long idTache) {
		// TODO Auto-generated method stub
		Tache tache= this.findTache(idTache);
		if(tache!=null) {
		if(tache.getTachePrecedente()!=null) {
			System.out.println("############"+ tache.getTachePrecedente());
			return tache.getTachePrecedente();
		}else {
			return null;
		}
		}
		return null;
	}

	@Override
	public Tache addJalon(Tache tache) {
		// TODO Auto-generated method stub
		Tache jalon=new Tache();
		jalon=tache;
		jalon.setDuree(0);
		jalon.setFinTache(null);
		jalon.setType("Jalon");
		
		return tacheRepository.save(jalon);
	}

	@Override
	public List<Tache> potentielPredecesseurs(Long idTache) {
		// TODO Auto-generated method stub
		List<Tache> potentiel = new ArrayList<>();
		Tache tache= this.findTache(idTache);
		if(tache!=null) {
			potentiel = tacheRepository.getPotentielPredecessor(tache.getPhase().getProjet().getNumProjet(), idTache);
			System.out.println(potentiel);
			return potentiel;
		}
		return null;
	}

	@Override
	public Tache addCommentToTask(List<Commentaire> comments) {
		Tache task=new Tache();
		// TODO Auto-generated method stub
		for(Commentaire com:comments) {
			
				Commentaire comen=	commentService.addComment(com);
				task = comen.getTacheComment();
			
		}
		return task;
	}

	@Override
	public List<Depense> getDepensesOfTask(Long idTache) {
		// TODO Auto-generated method stub
		Tache tache = this.findTache(idTache);
		if(tache!=null) {
			tache.getDepenses();
		}
		return null;
	}

	@Override
	public Facture getFactureOfTasK(Long idTache) {
		// TODO Auto-generated method stub
		Tache tache =findTache(idTache);
		if(tache != null) {
			return tache.getFacture();
		}
		return null;
	}

	@Override
	public double calculCoutRessourcesOfTask(Long idTache) {
		// Calcul du coup du cout d'une tache 
		double totale=0;
		List<AffectationUtilisateur> affectations =affectService.getAffectationsForTache(idTache);
		if(!affectations.isEmpty()) {
			for(AffectationUtilisateur affect : affectations) {
				totale = totale + (affect.getCoutParHeure() * affect.getTempsEffectuer());
			}
		}
		return totale;
	}

	@Override
	public double calculEstimationCoutTache(Long idTache) {
		// Calcul du coup provisoire du cout d'une tache 
		double estimation=0;
		List<AffectationUtilisateur> affectations =affectService.getAffectationsForTache(idTache);
		if(!affectations.isEmpty()) {
			for(AffectationUtilisateur affect : affectations) {
				estimation = estimation + (affect.getCoutParHeure() * affect.getTempsPasser());
			}
		}
		return estimation;
	}

	@Override
	public double getCoutTotaleDepense(Long idTache) {
		// TODO Auto-generated method stub
		double totale =0;
		List<Depense> depenses = this.getDepensesOfTask(idTache);
		if(!depenses.isEmpty()) {
			for(Depense dep : depenses) {
				totale = totale + dep.getCoutDepense();
			}
		}
		return totale;
	}



}
