package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Tache implements Serializable {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	@Column(name="num_tache")
	private Long numTache; 
	
	@Column(name="nom_tache")
	private String nomTache;
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name="debut_tache")
	private Date debutTache;
	@Temporal(TemporalType.DATE)
	@Column(name="fin_tache")
	private Date finTache;
	@Column(name="taux_avancement")
	private double tauxAvancement;
	@Column(name="charge_tache")
	private double chargeTache;
	@Column(name="niveau_priorite")
	private String niveauPriorite;
	private int duree;
	private String type;
	
	// Derniere modification effecuter sur la tache
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;
	
	@ManyToOne
	@JsonBackReference(value="tache-phase")
	@JoinColumn(name ="phase_num_tache")
	private Phase phase;
	
	@JsonManagedReference(value="facture-tache")
	@OneToOne(mappedBy = "tache")
	private Facture facture;
	
	
	@OneToMany(mappedBy = "tache", fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value="depense-tache")
	private List<Depense> depenses;
	
	@JsonManagedReference(value="fichier-tache")
	@OneToMany(mappedBy = "tacheConcerne", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Fichier> fichiers;
	
	//Commnetaire de tache
	@JsonSetter
	//@JsonManagedReference(value="tache-comment")
	@JsonIgnore
	@OneToMany(mappedBy = "tacheComment", fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Commentaire> commentaires;
	
	
	
	/*le createur d'une tache */
	@ManyToOne
	@JsonBackReference(value="tache-user")
	@JoinColumn(name ="createur_id_user")
	private Utilisateur createur;
	
	
	@JoinTable(name = "Tache_Predecesseurs", joinColumns = {
		    @JoinColumn(name = "tache", referencedColumnName = "num_tache", nullable =   false)}, inverseJoinColumns = {
		    @JoinColumn(name = "Predecesseur", referencedColumnName = "num_tache", nullable = false)})
		    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.DETACH)
		    private List<Tache> tachePrecedente;

	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Tache(String nomTache, Date debutTache, Date finTache, double tauxAvancement, double chargeTache,
			String niveauPriorite, int duree, String type,Phase phase, Facture facture, List<Depense> depenses,
			List<Fichier> fichiers, List<Tache> tachePrecedente,List<Commentaire> commentaires,Utilisateur createur,
			 Date lastUpdate) {
		super();
		this.nomTache = nomTache;
		this.debutTache = debutTache;
		this.finTache = finTache;
		this.tauxAvancement = tauxAvancement;
		this.chargeTache = chargeTache;
		this.niveauPriorite = niveauPriorite;
		this.duree = duree;
		this.lastUpdate = lastUpdate;
		this.type=type;
		this.phase = phase;
		this.facture = facture;
		this.depenses = depenses;
		this.fichiers = fichiers;
		this.tachePrecedente = tachePrecedente;
		this.commentaires=commentaires;
		this.createur = createur;
		
	}


	public String getNomTache() {
		return nomTache;
	}



	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}



	public Date getDebutTache() {
		return debutTache;
	}




	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public void setDebutTache(Date debutTache) {
		this.debutTache = debutTache;
	}



	public Date getFinTache() {
		return finTache;
	}



	public void setFinTache(Date finTache) {
		this.finTache = finTache;
	}



	public double getTauxAvancement() {
		return tauxAvancement;
	}



	public void setTauxAvancement(double tauxAvancement) {
		this.tauxAvancement = tauxAvancement;
	}



	public double getChargeTache() {
		return chargeTache;
	}



	public void setChargeTache(double chargeTache) {
		this.chargeTache = chargeTache;
	}



	public String getNiveauPriorite() {
		return niveauPriorite;
	}



	public void setNiveauPriorite(String niveauPriorite) {
		this.niveauPriorite = niveauPriorite;
	}



	public Phase getPhase() {
		return phase;
	}



	public void setPhase(Phase phase) {
		this.phase = phase;
	}



	public Facture getFacture() {
		return facture;
	}



	public void setFacture(Facture facture) {
		this.facture = facture;
	}



	public List<Depense> getDepenses() {
		return depenses;
	}



	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}



	public List<Fichier> getFichiers() {
		return fichiers;
	}



	public void setFichiers(List<Fichier> fichiers) {
		this.fichiers = fichiers;
	}



	public int getDuree() {
		return duree;
	}
	public Long getNumTache() {
		return numTache;

	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public void setNumTache(Long numTache) {
		this.numTache = numTache;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}




	public List<Tache> getTachePrecedente() {
		return tachePrecedente;
	}


	public void setTachePrecedente(List<Tache> tachePrecedente) {
		this.tachePrecedente = tachePrecedente;
	}


	public List<Commentaire> getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}


	public Utilisateur getCreateur() {
		return createur;
	}


	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}


	public Date getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	
}
