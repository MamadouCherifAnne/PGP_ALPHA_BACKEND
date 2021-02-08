package com.iscae.alpha.pgp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.iscae.alpha.pgp.dao.FichierRepository;
import com.iscae.alpha.pgp.entities.Fichier;
import com.iscae.alpha.pgp.entities.Tache;

@Service
public class FichierServiceImp implements FichierService {
	
	@Autowired
	private FichierRepository fichierRepository;
	@Override
	public Fichier upload(Fichier file) {
		return fichierRepository.save(file);
	}

	@Override
	public  Stream<Fichier> findAll() {
		// TODO Auto-generated method stub
		return fichierRepository.findAll().stream();
	}

	@Override
	public Fichier fidById(Long idFichier) {
		Optional<Fichier> file = fichierRepository.findById(idFichier);
		if(file.isPresent()) {
			return file.get();
		}else {
			return null;
		}
	}

	@Override
	public boolean deleteFile(Long idFichier) {
		Fichier file = fidById(idFichier);
		if(file != null ) {
			fichierRepository.delete(file);
			return true;
		}
		return false;
	}	

}
