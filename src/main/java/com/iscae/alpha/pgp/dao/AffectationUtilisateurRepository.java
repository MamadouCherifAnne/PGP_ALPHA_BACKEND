package com.iscae.alpha.pgp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;

public interface AffectationUtilisateurRepository extends JpaRepository<AffectationUtilisateur, UserToTache>{
 
	
}
