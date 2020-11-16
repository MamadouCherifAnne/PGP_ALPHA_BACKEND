package com.iscae.alpha.pgp.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;

import com.iscae.alpha.pgp.mail.Mail;

public interface MailServiceInterface {
	// ENvoyer u message simple
	public String SendMessage( Mail email);
// Envoyer un message avec un fichier joint
	public String SendMessageWithAttachement(Mail email);

}
