package com.iscae.alpha.pgp.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Phase  implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="num_tache")
	private Long numTache;
	@Column(name="nom_tache")
	private String nomTache;
	
	private String description;
	@Column(name="debut_tache")
	private Date debutTache;
	
	
	@JsonBackReference(value="phase-projet")
	@ManyToOne
	@JoinColumn(name ="projet_num_projet")
	private Projet projet;


	@JsonManagedReference(value="tache-phase")
	@OneToMany(mappedBy = "phase")
	private List<Tache> taches;

	public Phase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phase(String nomTache, String description, Projet projet, List<Tache> tache,Date debutTache) {
		super();
		this.nomTache = nomTache;
		this.description = description;
		this.projet = projet;
		this.taches = tache;
		this.debutTache=debutTache;
	}
	
	

	public Long getNumTache() {
		return numTache;
	}


	public void setNumTache(Long numTache) {
		this.numTache = numTache;
	}

	public Date getDebutTache() {
		return debutTache;
	}

	public void setDebutTache(Date debutTache) {
		this.debutTache = debutTache;
	}



	public String getNomTache() {
		return nomTache;
	}

	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public List<Tache> getTache() {
		return taches;
	}

	public void setTache(List<Tache> tache) {
		this.taches = tache;
	}

	
}
