package com.iscae.alpha.pgp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.MonUserDetail;
import com.iscae.alpha.pgp.dao.UtilisateurRepository;
import com.iscae.alpha.pgp.entities.Utilisateur;

@Service
public class MonUserDetailService implements UserDetailsService {
	
	@Autowired
	UtilisateurRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> user=userRepository.findByNom(username);
		
		user.orElseThrow(()-> new UsernameNotFoundException("impossible de trouver :"+username));
		return user.map(MonUserDetail::new).get();
	}

}
