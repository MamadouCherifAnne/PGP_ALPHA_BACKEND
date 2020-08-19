package com.iscae.alpha.pgp.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Fichier implements Serializable {
	@Id
	@GeneratedValue
	private Long NumFichier;
    private String type;
    

    @Lob 
    @Column(name = "logo", columnDefinition = "LONGBLOB")
	private byte[] logo;
	 
	private String nomFichier;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Tache tacheConcerne;

	public Fichier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	
	public Fichier(String nomFichier,String type, byte[] data, Tache tacheConcerne) {
		super();
		this.nomFichier = nomFichier;
		this.logo = data;
		this.type = type;
		this.tacheConcerne = tacheConcerne;
	}



	public Long getNumFichier() {
		return NumFichier;
	}

	public void setNumFichier(Long numFichier) {
		NumFichier = numFichier;
	}

	
	public String getType() {
		return type;
	} 



	public void setType(String type) {
		this.type = type;
	} 

	public byte[] getLogo() {
		return logo;
	}



	public void setLogo(byte[] logo) {
		this.logo = logo;
	}



	public String getNomFichier() {
		return nomFichier;
	}



	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}



	public Tache getTacheConcerne() {
		return tacheConcerne;
	}

	public void setTacheConcerne(Tache tacheConcerne) {
		this.tacheConcerne = tacheConcerne;
	}
	
	
}
