package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.service.CommentaireService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/commentaire")
public class CommentaireController {
	@Autowired
	CommentaireService commentService;
	
	@DeleteMapping(value="/deleteComment/{idComment}")
	public boolean deleteCommentaire(@PathVariable Long idComment) {
		return commentService.deleteComment(idComment);
	}

}
