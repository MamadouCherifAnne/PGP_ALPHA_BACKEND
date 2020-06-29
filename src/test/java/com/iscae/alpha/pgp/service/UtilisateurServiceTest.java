package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;


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
		

		Utilisateur user=getUtilisateur();
		user.setIdUser(1L);
		Mockito.when(userRepo.getOne(1L)).thenReturn(user);
		Mockito.when(userRepo.existsById(user.getIdUser())).thenReturn(false);
		assertTrue(userService.deleteUser(1L));

	}
	
	// Test du service modification des informations d'un utilsateur
	@Test
	public void UpdateUtilisateurTest() {

		Utilisateur user=getUtilisateur();
		user.setIdUser(1L);
		
		Mockito.when(userRepo.getOne(1L)).thenReturn(user);
		user.setEmail("mmd@gmail.com");
		Mockito.when(userRepo.save(user)).thenReturn(user);
		
		
		assertThat(userService.updateUser(user)).isEqualTo(userRepo.save(user));
	}
	
	
	// Test du service Get ALL USERS
	@Test
	public void getAllUsersTest() {
		Utilisateur user1= getUtilisateur();
		Utilisateur user2 =getUtilisateur();
		user2.setNom("Covid");
		user2.setEmail("covid19@world.com");
		user2.setAdresse("China");
		List<Utilisateur> userList=new ArrayList<>();
		userList.add(user1); userList.add(user2);
		
		Mockito.when(userRepo.findAll()).thenReturn(userList);
		assertThat(userService.getAllUsers()).isEqualTo(userList);
		
	}
	
	// Test De la method erecherche par nom 
	@Test
	public void getUserByNomTest() {
		Utilisateur user =getUtilisateur();
		user.setIdUser(1L);
		Mockito.when(userRepo.findByNom("Bilalapgp")).thenReturn(user);
		assertThat(userService.getUserByName("Bilalapgp")).isEqualTo(user);
	}
	
	// preparation d'un utilisateur
	public Utilisateur getUtilisateur() {
		Utilisateur user=new Utilisateur();
		user.setNom("Bilalapgp");

		user.setAdresse("KSAR");
		user.setActif(true);
		user.setPrenom("Samba");
		user.setTelephone("454566");
		user.setCommentaires(null);
		user.setEntreprises(null);
		user.setEmail("pgp@gamil.com");
		return user;
	}

}
