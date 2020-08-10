package com.iscae.alpha.pgp.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iscae.alpha.pgp.entities.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {

	
	Tache findTahceByDebutTache(Date dateDebut);
	Tache findTacheByfinTache(Date dateFin);
	
	


}
