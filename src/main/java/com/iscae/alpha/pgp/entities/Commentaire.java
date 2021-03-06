package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class Commentaire implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_comment")
	private Long idComment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_comment",nullable = false)
	private Date dateComment;
	@Column(name="comment")
	private String comment;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id_user")
	private Utilisateur user;
	
	@JsonBackReference(value="tache-comment")
	@ManyToOne
	@JoinColumn(name="tache_comment_num_tache")
	
	private Tache tacheComment;
	
	@JsonBackReference(value="projet-comment")
	@ManyToOne
	@JoinColumn(name="projet_comment_num_projet")
	private Projet projetComment;


	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(Date dateComment, String comment, Utilisateur user, Tache tacheComment,Projet projetComment) {
		super();
		this.dateComment = dateComment;
		this.comment = comment;
		this.user = user;
		this.tacheComment=tacheComment;
		this.projetComment=projetComment;
	}
	
	
	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public Date getDateComment() {
		return dateComment;
	}

	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Tache getTacheComment() {
		return tacheComment;
	}

	public void setTacheComment(Tache tacheComment) {
		this.tacheComment = tacheComment;
	}

	public Projet getProjetComment() {
		return projetComment;
	}

	public void setProjetComment(Projet projetComment) {
		this.projetComment = projetComment;
	}

	
}
