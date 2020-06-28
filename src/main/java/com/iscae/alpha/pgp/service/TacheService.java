package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import com.iscae.alpha.pgp.entities.Tache;

public interface TacheService {
	
	Tache addTache(Tache tache);


	boolean updateTache( Tache tache);

	void deleateTache(Long idTache);
	Tache findTache(Long idTache);
	Tache findTahceByDebut(Date dateDebut);
	Tache findTacheByFin(Date dateFin);
	List<Tache> findAllTache();
}
