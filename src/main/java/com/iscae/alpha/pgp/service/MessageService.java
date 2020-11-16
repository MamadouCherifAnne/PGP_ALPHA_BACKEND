package com.iscae.alpha.pgp.service;

import com.iscae.alpha.pgp.entities.Message;

public interface MessageService {
	// Ajout D'un message
	public Message addMessage(Message message);
	
	// Update etat du message
	public void modifierEtat(Long idMessage);
	
	// Suppression d'un Message
	public boolean deleteMessage(Long idMessage);
}
