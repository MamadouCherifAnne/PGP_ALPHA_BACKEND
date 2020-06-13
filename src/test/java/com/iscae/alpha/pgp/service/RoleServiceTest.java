package com.iscae.alpha.pgp.service;

import static org.assertj.core.api.Assertions.assertThat;

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
		
		
		public Role getRole() {
			Role role=new Role();
			role.setRole("Collaborateurs");
			
		return role;
		}
		
}
