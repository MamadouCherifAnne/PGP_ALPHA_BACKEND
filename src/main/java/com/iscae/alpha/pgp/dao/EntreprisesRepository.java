package com.iscae.alpha.pgp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Entreprise;

public interface EntreprisesRepository extends JpaRepository<Entreprise, Long> {
	
	public Optional<Entreprise> findByNameEntreprise(String nameEntreprise);
}
