package com.iscae.alpha.pgp.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iscae.alpha.pgp.entities.Fichier;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.service.FichierServiceImp;
import com.iscae.alpha.pgp.service.TacheServiceImpl;

import message.ResponseFile;
import message.ResponseMessage;

@RestController
@RequestMapping("/fichier")
@CrossOrigin(origins="*")
public class FichierController {
	@Autowired
	private FichierServiceImp fichierservice;
	
	@Autowired 
	private TacheServiceImpl tacheService;
	
	@PostMapping("/upload/{tacheId}")
	public  @ResponseBody ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable Long tacheId) throws IOException {
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		 Tache tache1 = tacheService.findTache(tacheId);
		 Fichier fichier = new Fichier();
		 fichier.setTacheConcerne(tache1);
		 fichier.setNomFichier(fileName); 
		 fichier.setLogo(file.getBytes());
		 fichier.setType(file.getContentType()); 
		 String message = "";
	    try { 
	    	fichierservice.upload(fichier);
	    	message = "Fichier chargé avec succés: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      
	    } catch (Exception e) {
	      message = "Le fichier: " + file.getOriginalFilename() + "n'a pas pu être charger!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	 
	 @GetMapping("/allfiles")
	  public ResponseEntity<List<ResponseFile>> getListFiles() {
	    List<ResponseFile> files = fichierservice.findAll().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getNumFichier().toString())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getNomFichier(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getLogo().length);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }

	  @GetMapping("/findFileById/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	    Fichier file = fichierservice.fidById(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getNomFichier() + "\"")
	        .body(file.getLogo());
	  }
}
