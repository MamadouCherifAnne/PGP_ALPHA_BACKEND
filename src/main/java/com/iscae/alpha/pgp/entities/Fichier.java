package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Fichier implements Serializable {
	@Id
	@GeneratedValue
	@Column(name ="num_fichier")
	private Long numFichier;
    private String type;
    

    @Lob 
    @Column(name = "logo", columnDefinition = "LONGBLOB")
	private byte[] logo;
	@Column(name = "nom_fichier")
	private String nomFichier;
	
	@JsonBackReference(value="fichier-tache")
	@ManyToOne
	@JoinColumn(name ="tache_concerne_num_tache")
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
		return numFichier;
	}



	public void setNumFichier(Long numFichier) {
		this.numFichier = numFichier;
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
