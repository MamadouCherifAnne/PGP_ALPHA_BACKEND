package com.iscae.alpha.pgp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long>{

	Optional<Utilisateur> findByNom(String nom);
}