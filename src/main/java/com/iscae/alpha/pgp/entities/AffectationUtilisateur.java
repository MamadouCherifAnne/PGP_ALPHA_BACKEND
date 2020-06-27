package com.iscae.alpha.pgp.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AffectationUtilisateur implements Serializable {

	private static final Long serialVersionUID =1L;


	@EmbeddedId
	private UserToTache user_task;
	
	private double tempsPasser;

	public AffectationUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffectationUtilisateur(UserToTache user_task, double tempsPasser) {
		super();
		this.user_task = user_task;
		this.tempsPasser = tempsPasser;
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
