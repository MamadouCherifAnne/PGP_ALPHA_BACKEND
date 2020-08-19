package com.iscae.alpha.pgp.service;

import com.iscae.alpha.pgp.entities.Message;

public interface MessageService {
	// Ajout D'un message
	public Message addMessage(Message message);
	
	// Suppression d'un Message
	public boolean deleteMessage(Long idMessage);
}
