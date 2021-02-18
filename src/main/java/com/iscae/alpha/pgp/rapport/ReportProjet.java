package com.iscae.alpha.pgp.rapport;

import java.util.Date;

public class ReportProjet {
	private String projectName;
	private String taskName;
	private String phaseName;
	private String statut;
	private String avancemant;
	private Date dateDebutProjet;
	private Date dateFinProjet;
	private Date dateDebutTask;
	private Date dateFinTask;
	private String priorite;
	
	public ReportProjet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateDebutProjet() {
		return dateDebutProjet;
	}

	public void setDateDebutProjet(Date dateDebutProjet) {
		this.dateDebutProjet = dateDebutProjet;
	}


	public Date getDateFinTask() {
		return dateFinTask;
	}

	public void setDateFinTask(Date dateFinTask) {
		this.dateFinTask = dateFinTask;
	}

	public String getPriorite() {
		return priorite;
	}

	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}

	public Date getDateDebutTask() {
		return dateDebutTask;
	}

	public void setDateDebutTask(Date dateDebutTask) {
		this.dateDebutTask = dateDebutTask;
	}

	public String getAvancemant() {
		return avancemant;
	}

	public void setAvancemant(String avancemant) {
		this.avancemant = avancemant;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getDateFinProjet() {
		return dateFinProjet;
	}

	public void setDateFinProjet(Date dateFinProjet) {
		this.dateFinProjet = dateFinProjet;
	}
	
	
	
}
