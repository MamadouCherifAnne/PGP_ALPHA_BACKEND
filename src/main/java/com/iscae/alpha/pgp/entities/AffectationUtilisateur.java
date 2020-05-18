package com.iscae.alpha.pgp.entities;

import javax.persistence.EmbeddedId;

public class AffectationUtilisateur {

	@EmbeddedId
	private AffectationUtilisateur user_task;
	
	private double tempsPasser;

	public AffectationUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffectationUtilisateur(AffectationUtilisateur user_task, double tempsPasser) {
		super();
		this.user_task = user_task;
		this.tempsPasser = tempsPasser;
	}

	public AffectationUtilisateur getUser_task() {
		return user_task;
	}

	public void setUser_task(AffectationUtilisateur user_task) {
		this.user_task = user_task;
	}

	public double getTempsPasser() {
		return tempsPasser;
	}

	public void setTempsPasser(double tempsPasser) {
		this.tempsPasser = tempsPasser;
	}
	
}
