package com.iscae.alpha.pgp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Phase;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
	
}
