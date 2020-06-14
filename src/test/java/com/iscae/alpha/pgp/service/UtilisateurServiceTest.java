package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Utilisateur;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilisateurServiceTest {
	@Autowired 
	UtilisateurService userService;
	
	@MockBean
	UtilisateurRepository userRepo;
	
	//Test de la methode ajout d'un utilisateur de la classe  Service utilisateur
	@Test
	public void addUtilisateurTest() {
		Utilisateur user = getUtilisateur();
		
		Mockito.when(userRepo.save(user)).thenReturn(user);
		assertThat(userService.addUser(user)).isEqualTo(user);
		
	}
	
	//test du service suppression
	
	@Test
	public void DeleteUserTest() {
		
		
	}
	
	// Test du service modification des informations d'un utilsateur
	@Test
	public void UpdateUtilisateurTest() {
		
	}
	
	
	public Utilisateur getUtilisateur() {
		Utilisateur user=new Utilisateur();
		user.setNom("Bilala@pgp");
		user.setAdresse("KSAR");
		user.setActif(true);
		user.setPrenom("Samba");
		user.setTelephone("454566");
		user.setCommentaire(null);
		user.setEntreprise(null);
		user.setEmail("pgp@gamil.com");
		return user;
	}

}
