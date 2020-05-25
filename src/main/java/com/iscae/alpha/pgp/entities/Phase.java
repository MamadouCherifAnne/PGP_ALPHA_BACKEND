package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Phase  implements Serializable {
	@Id
	@GeneratedValue
	private Long NumPhase;
	private String titrePhase;
	private String description;
	
	@ManyToOne
	private Projet projet;

}
