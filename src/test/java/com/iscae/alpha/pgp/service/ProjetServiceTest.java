 package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.entities.Projet;

@ExtendWith(SpringExtension.class)
@SpringBootTest 
class ProjetServiceTest {
	
	@Autowired
	private ProjetService projetService; 
	
	@MockBean
	private ProjetRepository projetRepository;
	
	@Test
	public void AddProjetTest() {
		Projet projet = new Projet();
  
		projet.setNumProjet(1L);
		projet.setDebutProjet(null);
		projet.setFinProjet(null);
		projet.setNomProjet("first projet");
		projet.setPhases(null);
		projet.setResponsable(null);
		projet.setRisques(null);
		projet.setZoneRealisation("Kaedi");
		
		Mockito.when(projetRepository.save(projet)).thenReturn(projet);
		assertThat(projetService.AddProjet(projet)).isEqualTo(projet);
	}
	
	@Test
	public void UpdateProjetTest() {
		Projet projet = new Projet();
		projet.setNumProjet(1L);
		projet.setDebutProjet(null);
		projet.setFinProjet(null);
		projet.setNomProjet("first projet");
		projet.setPhases(null);
		projet.setResponsable(null);
		projet.setRisques(null);
		projet.setZoneRealisation("Kaedi");
		
		Projet projet1 = new Projet();
		
		
		
		Date date1 = new Date(2014, 12, 12);
		Date date2 = new Date(2011, 11, 12);
		
		Mockito.when(projetRepository.findByDebutProjet(date1)).thenReturn(projet);
		projet.setDebutProjet(date2);
		Mockito.when(projetRepository.save(projet)).thenReturn(projet);
		projet1 = projet;
		
		
	}
	
	@Test
	public void deleateProjetTest() {
		
	}
}
