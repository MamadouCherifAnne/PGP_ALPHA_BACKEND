package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.iscae.alpha.pgp.dao.AffectationUtilisateurRepository;
import com.iscae.alpha.pgp.entities.AffectationUtilisateur;
import com.iscae.alpha.pgp.entities.UserToTache;

public class AffectationUtilisateurTest {
	@MockBean
	AffectationUtilisateurRepository affectRepo;
	
	@Autowired
	AffectationUtilisateurService affectService;
	
	
	
	

	
	// Methode de Test du Service d'ajout d' une nouvelle affectation
	@Test
	public void addAffectationTest() {
		AffectationUtilisateur affectation= getAffectation();
		Mockito.when(affectRepo.save(affectation)).thenReturn(affectation);
		assertThat(affectService.addAffectationUser(affectation)).isEqualTo(affectation);
	}
	
	@Test
	public void updateAffectationTest() {
		
	}
	
	public AffectationUtilisateur getAffectation() {
		 
		AffectationUtilisateur affectation=new AffectationUtilisateur();
		UserToTache userForTache =new UserToTache(1L,1L);
		affectation.setUser_task(userForTache);
		affectation.setTempsPasser(89);
		return  affectation;
	}

}
