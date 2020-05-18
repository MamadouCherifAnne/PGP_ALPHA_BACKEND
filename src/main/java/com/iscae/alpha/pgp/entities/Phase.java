package com.iscae.alpha.pgp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Phase {
	@Id
	@GeneratedValue
	private Long NumPhase;
	private String titrePhase;
	private String description;
	
	@ManyToOne
	private Projet projet;

}
