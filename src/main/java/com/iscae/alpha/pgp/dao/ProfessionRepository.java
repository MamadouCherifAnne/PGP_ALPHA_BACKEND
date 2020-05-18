package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
