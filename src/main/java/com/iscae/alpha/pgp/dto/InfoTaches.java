package com.iscae.alpha.pgp.dto;

public class InfoTaches {
	
	private int nbrTachesEnCours;
	private int nbrTacesTerminees;
	private int nbrTachesEnRetards;
	
	public InfoTaches() {
	}

	public InfoTaches(int nbrTachesEnCours, int nbrTacesTerminees, int nbrTachesEnRetards) {
		super();
		this.nbrTachesEnCours = nbrTachesEnCours;
		this.nbrTacesTerminees = nbrTacesTerminees;
		this.nbrTachesEnRetards = nbrTachesEnRetards;
	}

	public int getNbrTachesEnCours() {
		return nbrTachesEnCours;
	}

	public void setNbrTachesEnCours(int nbrTachesEnCours) {
		this.nbrTachesEnCours = nbrTachesEnCours;
	}

	public int getNbrTacesTerminees() {
		return nbrTacesTerminees;
	}

	public void setNbrTacesTerminees(int nbrTacesTerminees) {
		this.nbrTacesTerminees = nbrTacesTerminees;
	}

	public int getNbrTachesEnRetards() {
		return nbrTachesEnRetards;
	}

	public void setNbrTachesEnRetards(int nbrTachesEnRetards) {
		this.nbrTachesEnRetards = nbrTachesEnRetards;
	}
	
	
}
