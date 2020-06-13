package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {

}
