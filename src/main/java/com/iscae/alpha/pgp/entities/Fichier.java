package com.iscae.alpha.pgp.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Fichier implements Serializable {
	@Id
	@GeneratedValue
	private Long NumFichier;
	private File file;
	private String descriptionFile;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Tache tacheConcerne;

	public Fichier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	
	public Fichier(File file, String descriptionFile, Tache tacheConcerne) {
		super();
		this.file = file;
		this.descriptionFile = descriptionFile;
		this.tacheConcerne = tacheConcerne;
	}



	public Long getNumFichier() {
		return NumFichier;
	}

	public void setNumFichier(Long numFichier) {
		NumFichier = numFichier;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDescriptionFile() {
		return descriptionFile;
	}

	public void setDescriptionFile(String descriptionFile) {
		this.descriptionFile = descriptionFile;
	}

	public Tache getTacheConcerne() {
		return tacheConcerne;
	}

	public void setTacheConcerne(Tache tacheConcerne) {
		this.tacheConcerne = tacheConcerne;
	}
	
	
}
