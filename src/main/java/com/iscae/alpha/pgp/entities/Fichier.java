package com.iscae.alpha.pgp.entities;

import java.io.File;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fichier {
	@Id
	@GeneratedValue
	private Long NumFichier;
	private File file;
	private String descriptionFile;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Tache tacheConcerne;
	

}
