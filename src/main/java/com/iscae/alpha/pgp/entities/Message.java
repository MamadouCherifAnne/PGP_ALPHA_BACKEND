package com.iscae.alpha.pgp.entities;

import java.util.Date;

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

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_message")
	private Long idMessage;
	@Column(name="message_content")
    private String messageContent;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_envoie")
    private Date dateEnvoie;
    
    @JsonBackReference(value="user-send")
    @ManyToOne
    @JoinColumn(name = "edit_user_id_user")
 
    private Utilisateur editUser;
    @JsonBackReference(value="user-receive")
    @ManyToOne
    @JoinColumn(name ="destinataire_id_user")
    private Utilisateur destinataire;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(String messageContent, Date dateEnvoie, Utilisateur editUser, Utilisateur destinataire) {
		super();
		this.messageContent = messageContent;
		this.dateEnvoie = dateEnvoie;
		this.editUser = editUser;
		this.destinataire = destinataire;
	}
	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getDateEnvoie() {
		return dateEnvoie;
	}
	public void setDateEnvoie(Date dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}
	public Utilisateur getEditUser() {
		return editUser;
	}
	public void setEditUser(Utilisateur editUser) {
		this.editUser = editUser;
	}
	public Utilisateur getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(Utilisateur destinataire) {
		this.destinataire = destinataire;
	}
    
    // Getters And Setters
	
}
