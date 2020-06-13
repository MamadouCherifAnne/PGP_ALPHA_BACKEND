package com.iscae.alpha.pgp.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Utilisateur;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

	Projet findByDebutProjet(Date date);
}
