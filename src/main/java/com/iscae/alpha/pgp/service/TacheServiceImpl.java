package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.PhaseRepository;
import com.iscae.alpha.pgp.dao.TacheRepository;
import com.iscae.alpha.pgp.entities.Tache;
@Service 
public class TacheServiceImpl implements TacheService{
	
	@Autowired
	private TacheRepository tacheRepository;
	
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
		Tache tache = tacheRepository.findById(idTache).get();
		return tache;
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

}
