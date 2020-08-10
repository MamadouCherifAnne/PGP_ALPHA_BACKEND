package com.iscae.alpha.pgp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.TacheRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
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
	
	@Override
	public Tache addTache(Tache tache) {
		return tacheRepository.save(tache);
	}

	@Override
	public boolean updateTache(Tache tache) {
		Optional<Tache> tache1 = tacheRepository.findById(tache.getNumTache());
		if(tache1 != null) {
			Tache tache2 = tache1.get();
			tache2.setNumTache(tache.getNumTache());
			tache2.setNomTache(tache.getNomTache());
			tache2.setChargeTache(tache.getChargeTache());
			tache2.setDebutTache(tache.getDebutTache());
			tache2.setFinTache(tache.getFinTache());
			tache2.setDepenses(tache.getDepenses());
			tache2.setDuree(tache.getDuree());
			tache2.setFacture(tache.getFacture());
			tache2.setNiveauPriorite(tache.getNiveauPriorite());
			tache2.setFichiers(tache.getFichiers());
			tache2.setTauxAvancement(tache.getTauxAvancement());
			tacheRepository.save(tache2);
			
			return true;
			
		}
		return false;
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
		if(tache.getTachePrecedente()!=null) {
			System.out.println("############"+ tache.getTachePrecedente());
			return tache.getTachePrecedente();
		}else {
			return null;
		}
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

}
