package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.entities.Role;

import com.iscae.alpha.pgp.entities.Utilisateur;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoleServiceTest {
		@Autowired 
		RoleService roleService;
		
		@MockBean
		RoleRepository roleRepo;
		
		//Test de la methode ajout d'un utilisateur de la classe  Service utilisateur
		@Test
		public void addRoleTets() {
			Role role = getRole();
			
			Mockito.when(roleRepo.save(role)).thenReturn(role);
			assertThat(roleService.addRole(role)).isEqualTo(role);
			
		}
		

		// Teste de la methode de modification dun role;
		@Test
		public void updateRoleTest() {
			Role role =getRole();
			role.setIdRole(1L);
			Mockito.when(roleRepo.getOne(1L)).thenReturn(role);
			role.setRole("mmd@gmail.com");
			Mockito.when(roleRepo.save(role)).thenReturn(role);
			
			
			assertThat(roleService.updateRole(role)).isEqualTo(roleRepo.save(role));
		}
		
		//test du service suppression
		
		@Test
		public void DeleteUserTest() {
			
			Role role=getRole();
			role.setIdRole(1L);
			Mockito.when(roleRepo.getOne(1L)).thenReturn(role);
			Mockito.when(roleRepo.existsById(role.getIdRole())).thenReturn(false);
			assertFalse(roleService.deleteRole(1L));
		}
		
		
		
		public Role getRole() {
			Role role=new Role();
			role.setIdRole(1L);
			role.setRole("Collaborateurs");
			
		return role;
		}
		
}
