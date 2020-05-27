package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.entities.Projet;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest	
public class ProjetServiceTest {

	
		@MockBean
		private ProjetRepository projetRepository;
		
		@Autowired 
		private ProjetService projetService;
		
		@Test()
		public void AddProjet() {
			Projet projet = new Projet();
			projet.setNumProjet(1L);
			projet.setDebutProjet(null);
			projet.setFinProjet(null);
			projet.setNomProjet("first projet");
			projet.setPhases(null);
			projet.setResponsables(null);
			projet.setResponsables(null);
			projet.setRisques(null);
			projet.setZoneRealisation("Kaedi");
			projetRepository.save(projet);
			
			Mockito.when(projetRepository.save(projet)).thenReturn(projet);
			assertThat(projetService.AddProjet(projet)).isEqualTo(projet);
			
		}
		
		
		
		
	


}
