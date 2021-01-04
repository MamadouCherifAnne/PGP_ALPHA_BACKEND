package com.iscae.alpha.pgp.dto;

import java.util.List;

import com.iscae.alpha.pgp.entities.Depense;
import com.iscae.alpha.pgp.entities.Tache;

public class infoDepenseTache {
	private Tache tache;
	private Long idProjet;
	private List<Depense> depenses;
	private double coutTotaleDepenses;
	public infoDepenseTache() {
		super();
		// TODO Auto-generated constructor stub
	}
	public infoDepenseTache(Tache tache, Long idProjet, double coutTotaleDepenses,List<Depense>depenses) {
		super();
		this.tache = tache;
		this.idProjet = idProjet;
		this.depenses = depenses;
		this.coutTotaleDepenses = coutTotaleDepenses;
	}
	
	
	
	public List<Depense> getDepenses() {
		return depenses;
	}
	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public double getCoutTotaleDepenses() {
		return coutTotaleDepenses;
	}
	public void setCoutTotaleDepenses(double coutTotaleDepenses) {
		this.coutTotaleDepenses = coutTotaleDepenses;
	}
	
	
}
