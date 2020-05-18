package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

}
