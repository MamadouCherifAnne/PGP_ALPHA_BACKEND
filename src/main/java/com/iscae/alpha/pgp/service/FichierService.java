package com.iscae.alpha.pgp.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.iscae.alpha.pgp.dao.FichierRepository;
import com.iscae.alpha.pgp.entities.Fichier;

public interface FichierService {

	//Ajouter un fichier 
	Fichier upload(Fichier file);
	//list de tous les fichier
	Stream<Fichier> findAll();
	//recuperer un fichier par son id
	Fichier fidById(Long idFichier);
	
	//supprimer un fichier
	boolean deleteFile(Long idFichier);
	
}
