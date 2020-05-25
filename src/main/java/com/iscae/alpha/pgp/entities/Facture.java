package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Facture implements Serializable {
	@Id
	@GeneratedValue
	private Long NumFacture;
	private String codeFacture;
	private double montantFacture;
	
	@OneToOne(mappedBy = "facture")
	private Tache tache;
	

}
