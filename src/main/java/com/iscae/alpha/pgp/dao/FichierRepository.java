package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Fichier;

public interface FichierRepository extends JpaRepository<Fichier, Long> {
	
}
