package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Message;
import com.iscae.alpha.pgp.service.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins="*")
public class MessageController {
	@Autowired
	MessageService msgService;
	
	// ENvoyer un message a un utilisateur
	@PostMapping(value="/sendMessage/")
	public Message sendMessage(@RequestBody Message message) {
		return msgService.addMessage(message);
	}
	//Suppression d'un message
	@PostMapping(value="/deleteMessage/{idMessage}")
	public String deleteMessage(@PathVariable Long idMessage) {
		boolean verif=false;
		verif = msgService.deleteMessage(idMessage);
		if(verif ==true) {
			return "Success";
		}else {
			return "La suppression du message a echouee";
		}
		}
	
	

}
