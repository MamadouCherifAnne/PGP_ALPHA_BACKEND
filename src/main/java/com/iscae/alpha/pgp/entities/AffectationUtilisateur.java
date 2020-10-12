package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AffectationUtilisateur implements Serializable {

	private static final Long serialVersionUID =1L;


	@EmbeddedId
	private UserToTache user_task;
	@Column(name="temps_passer")
	private double tempsPasser;
	@Column(name="temps_effectuer")
	private double tempsEffectuer;
	@Column(name ="cout_par_heure")
	private double coutParHeure;
	public AffectationUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffectationUtilisateur(UserToTache user_task, double tempsPasser, double tempsEffectuer, double coutParHeure) {
		super();
		this.user_task = user_task;
		this.tempsPasser = tempsPasser;
		this.tempsEffectuer =tempsEffectuer;
		this.coutParHeure =coutParHeure;
	}
	
	
	
	public double getCoutParHeure() {
		return coutParHeure;
	}

	public void setCoutParHeure(double coutParHeure) {
		this.coutParHeure = coutParHeure;
	}

	public double getTempsEffectuer() {
		return tempsEffectuer;
	}

	public void setTempsEffectuer(double tempsEffectuer) {
		this.tempsEffectuer = tempsEffectuer;
	}

	public UserToTache getUser_task() {
		return user_task;
	}

	public void setUser_task(UserToTache user_task) {
		this.user_task = user_task;
	}

	public double getTempsPasser() {
		return tempsPasser;
	}

	public void setTempsPasser(double tempsPasser) {
		this.tempsPasser = tempsPasser;
	}
	
}
