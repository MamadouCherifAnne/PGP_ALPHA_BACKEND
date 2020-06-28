package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.entities.Profession;
import com.iscae.alpha.pgp.entities.Role;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfessionServiceTest {
	
	@Autowired 
	ProfessionService profService;
	
	@MockBean
	ProfessionRepository profRepo;
	
	@Test
	public void addProfession() {
		Profession profession = getProfession();
		Mockito.when(profRepo.save(profession)).thenReturn(profession);
		assertThat(profService.addProfession(profession)).isEqualTo(profession);
		
	}
	
	@Test
	public void DeleteRoleTest() {
		
		Profession prof=getProfession();
		prof.setNumProfession(1L);
		Mockito.when(profRepo.getOne(1L)).thenReturn(prof);
		Mockito.when(profRepo.existsById(prof.getNumProfession())).thenReturn(false);
		assertFalse(profService.deleteProfession(1L));
	}
	
	
	// Test de la recuperation de tout les professions
	@Test
	public void getAllProfessionTest() {
		Profession prof1= getProfession();
		Profession prof2 =getProfession();
		prof2.setNumProfession(2L);
		prof2.setTitreProfession("DG");
		
		List<Profession> profList=new ArrayList<>();
		profList.add(prof1); profList.add(prof2);
		
		Mockito.when(profRepo.findAll()).thenReturn(profList);
		assertThat(profService.getAllProfession()).isEqualTo(profList);
		
	}
	
	// Test de la Methode de suppression
	@Test
	public void getProfessionByIdTest() {
		Profession prof = getProfession();
		
		
		Mockito.when(profRepo.existsById(prof.getNumProfession())).thenReturn(true);
		assertThat(profService.findProfessionById(1L));
	}


	// Methode de creation d'une profession pour le test
	public Profession getProfession() {
		Profession prof =new Profession();
		prof.setNumProfession(1L);
		prof.setTitreProfession("Bosseur");
		
		return prof;
	}
}
