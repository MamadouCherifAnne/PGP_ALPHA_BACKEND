package com.iscae.alpha.pgp.web;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.mail.Mail;
import com.iscae.alpha.pgp.service.MailServiceInterface;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins= "*")
public class MailController {
	
		@Autowired
		MailServiceInterface mailService;
		@Autowired
		private JavaMailSender javaMailSender;
		
		@PostMapping("/send")
		public String SendMessage(@RequestParam String to, @RequestParam String subject, @RequestParam String msg) {
			System.out.println(to+subject+msg);
			
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(to);
				message.setSubject(subject);
				message.setText(msg);
				javaMailSender.send(message);
				
				return "mail sended with succes";
			}catch(Exception e) {
				return e.getMessage() + "Sending Error";
			}
			
		}
		
		
		@PostMapping("/sendMail")
		public String SendMessage(@RequestBody Mail email) {
			return mailService.SendMessage(email);
		}
		
		@PostMapping("/sendMailAttachement")
		public String SendMessageWithAttachement(@RequestBody Mail email) {
			
			return mailService.SendMessageWithAttachement(email);
		}

}
