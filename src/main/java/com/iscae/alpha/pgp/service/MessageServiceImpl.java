package com.iscae.alpha.pgp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.MessageRepository;
import com.iscae.alpha.pgp.entities.Message;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository msgRepo;

	@Override
	public Message addMessage(Message message) {
		// AJout d'un message
		return msgRepo.save(message);
	}

	@Override
	public boolean deleteMessage(Long idMessage) {
		// Suppression d'un message
		Optional<Message> message = msgRepo.findById(idMessage);
		if(message.isPresent()) {
			msgRepo.deleteById(idMessage);
			return true;
		}
		return false;
	}

}
