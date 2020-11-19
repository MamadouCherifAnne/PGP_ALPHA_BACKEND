package com.iscae.alpha.pgp.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.mail.Mail;

@Service
public class MailServiceImpl implements MailServiceInterface {
	
	private static final Logger Log = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String SendMessage(Mail email) {
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getTo());
			message.setSubject(email.getSubject());
			message.setText(email.getBody());
			javaMailSender.send(message);
			
			return "mail sended with succes";
		}catch(Exception e) {
			return e.getMessage() + "Sending Error";
		}
	}

	@Override
	public String SendMessageWithAttachement(Mail email) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(email.getTo());
			helper.setSubject(email.getSubject());
			helper.setText(email.getBody());
			helper.setText(email.getBody(), true);
			//ClassPathResource path = new ClassPathResource("image.png");
			//helper.Attachement("image.png", path);
			
			javaMailSender.send(message);
			
			return "mail sended with succes";
		}catch(Exception e) {
			return e.getMessage() + "Sending Error";
		}
	}

}
