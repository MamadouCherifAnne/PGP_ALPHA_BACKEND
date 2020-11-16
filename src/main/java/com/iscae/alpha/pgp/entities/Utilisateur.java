package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Utilisateur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_user")
	private Long idUser;
	private String nom;
	@Column(unique = true)
	private String username;
	private String prenom;
	private String email;
	private String password;
	private String adresse;
	private String company;
	private boolean actif;
	private String telephone;
	
	
	// Liste des tache creer
	/*@JsonManagedReference(value="tache-createur")
	@OneToMany(mappedBy = "createur", fetch  = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<Tache> tachesCreer;
	*/

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Utilisateur_Profession",
	joinColumns = @JoinColumn(name="id_user"), inverseJoinColumns = @JoinColumn(name="num_profession"))
	@JsonSetter

	private List<Profession> professions;
	
	//Les projet don celui ci est responsable
	@OneToMany(mappedBy = "responsable",  fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value="responsable-projet")
	private List<Projet> projets;
	
	@OneToMany(mappedBy = "createur",  fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value="tache-user")
	private List<Tache> taches;
	
	

	@OneToMany(mappedBy = "user")
	private List<Rapport> rapports;
	
	@JsonSetter
	@JsonBackReference(value="user-commentaire")
	@OneToMany(mappedBy = "user", fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Commentaire> commentaires;
	
	
	// Les entreprises dedier a un utilisateur
	@JsonBackReference(value="user-entreprise")
	@ManyToOne
	@JoinColumn(name="entreprise_id_entreprise")
	private Entreprise entreprise;
	
	// Les Messages entre utilisateur
	@JsonSetter
	@JsonBackReference(value="user-send")
	@OneToMany(mappedBy = "editUser", fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> sendMessages;
	
	//Message received
	@JsonSetter
	@JsonBackReference(value="user-receive")
	@OneToMany(mappedBy = "destinataire", fetch  = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> messageReceived;
	

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(String nom, String prenom, String email, String username,String company,String password, String adresse, boolean actif,
			String telephone, List<Profession> professions,  List<Rapport> rapports, List<Projet> projets,
			List<Commentaire> commentaires, Entreprise entreprise,List<Message> sendMessages, List<Message> messageReceived,
			List<Tache> taches) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.actif = actif;
		this.username =username;
		this.company =company;
		this.telephone = telephone;
		this.taches =taches;
		this.professions = professions;
		this.projets =projets;
		this.rapports = rapports;
		this.commentaires = commentaires;
		this.entreprise = entreprise;
		this.sendMessages = sendMessages;
		this.messageReceived = messageReceived;
		
	}

	// GETTERS AND SETTERS ......................................................................................................
	
	
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public List<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}


	public List<Rapport> getRapports() {
		return rapports;
	}

	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<Message> getSendMessages() {
		return sendMessages;
	}

	public void setSendMessages(List<Message> sendMessages) {
		this.sendMessages = sendMessages;
	}

	public List<Message> getMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived(List<Message> messageReceived) {
		this.messageReceived = messageReceived;
	}


	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	
	
	
	
}