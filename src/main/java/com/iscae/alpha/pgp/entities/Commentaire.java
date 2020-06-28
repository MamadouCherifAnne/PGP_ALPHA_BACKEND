package com.iscae.alpha.pgp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Commentaire implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private Long idComment;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date dateComment;
	private String comment;
	
	@ManyToOne
	private Utilisateur user;

	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(Date dateComment, String comment, Utilisateur user) {
		super();
		this.dateComment = dateComment;
		this.comment = comment;
		this.user = user;
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

	
	

	
	
}
