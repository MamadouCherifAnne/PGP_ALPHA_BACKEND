package com.iscae.alpha.pgp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.service.CommentaireService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/commentaire")
public class CommentaireController {
	@Autowired
	CommentaireService commentService;
	

}
