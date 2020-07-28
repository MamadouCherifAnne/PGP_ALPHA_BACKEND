package com.iscae.alpha.pgp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.EntreprisesRepository;
import com.iscae.alpha.pgp.dao.RoleRepository;
import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Role;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service

public class EntrepriseServiceImplementation implements EntrepriseService {
	@Autowired
	EntreprisesRepository entrepriseRepo;
	@Autowired
	UtilisateurService userService;
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public Entreprise addEntreprise(Utilisateur proprietaire) {
		Entreprise espaceTravail=new Entreprise();
		Utilisateur user=proprietaire;
		
		// On lui accorde le role SUPER ADMIN lors de la creation de son espace de travail
		Role role=new Role();
		if(roleRepo.findByRole("SUPER_ADMIN").isPresent()) {
			role= roleRepo.findByRole("SUPER_ADMIN").get();
		}else {
			role.setRole("SUPER_ADMIN");
			role=roleRepo.save(role);
		}
		user.setRole(role);
		 espaceTravail=new Entreprise();
		espaceTravail=entrepriseRepo.save(user.getEntreprise());
		user=userService.addUser(user);
		if(user!=null) {
		return espaceTravail;}
		else {
			return null;
		}
				
	}

	@Override
	public Entreprise updateEntreprise(Entreprise entreprise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleateEntreprise(Long idEntreprise) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entreprise findEntrepriseById(Long idEntreprise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entreprise> findAllEntreprise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entreprise addSousEntreprise(Entreprise sousEntreprise) {
		// TODO Auto-generated method stub
		return null;
	}

}
