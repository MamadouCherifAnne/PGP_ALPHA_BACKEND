package com.iscae.alpha.pgp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Projet {
	@Id
	@GeneratedValue
	private Long NumProjet;
	
	private String nomProjet;
	
	@Temporal(TemporalType.DATE)
	private Date debutProjet;
	
	@Temporal(TemporalType.DATE)
	private Date finProjet;
	
	private String zoneRealisation;
	
	// Jai eu des doutes concernant la relation entre projet et utilisateurs 
	 @OneToMany(mappedBy = "projet")
	private List<Utilisateur> responsables;
	
	 @OneToMany(mappedBy = "projet")
	 private List<Risque> risques;
	 
	 @OneToMany(mappedBy = "projet")
	 private List<Phase> phases;
}
