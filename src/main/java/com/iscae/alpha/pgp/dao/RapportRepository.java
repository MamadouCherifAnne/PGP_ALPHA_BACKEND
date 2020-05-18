package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Rapport;

public interface RapportRepository extends JpaRepository<Rapport, Long> {

}
