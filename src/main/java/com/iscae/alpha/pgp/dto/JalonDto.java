package com.iscae.alpha.pgp.dto;

import com.iscae.alpha.pgp.entities.Tache;

import java.util.List;

public class JalonDto {
	private List<Tache> jalons;
	private List<Tache> jalonRetards;
	public JalonDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Tache> getJalons() {
		return jalons;
	}
	public void setJalons(List<Tache> jalons) {
		this.jalons = jalons;
	}
	public List<Tache> getJalonRetards() {
		return jalonRetards;
	}
	public void setJalonRetards(List<Tache> jalonRetards) {
		this.jalonRetards = jalonRetards;
	}
	
	

}
