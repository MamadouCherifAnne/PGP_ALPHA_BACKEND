package com.iscae.alpha.pgp.web;


import java.util.List;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iscae.alpha.pgp.ConstantWebApi;
import com.iscae.alpha.pgp.dao.ProfessionRepository;
import com.iscae.alpha.pgp.dao.TacheRepository;
import com.iscae.alpha.pgp.dto.InfosMessageNonLu;
import com.iscae.alpha.pgp.dto.MonTravail;
import com.iscae.alpha.pgp.dto.Role;
import com.iscae.alpha.pgp.dto.UtilisateurDto;
import com.iscae.alpha.pgp.entities.Entreprise;
import com.iscae.alpha.pgp.entities.Message;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;
import com.iscae.alpha.pgp.service.UtilisateurService;




@RestController
@RequestMapping("/utilisateur")

@CrossOrigin(origins = "http://localhost:4200")

public class UtilisateurController {
	
	//
	@Autowired

	UtilisateurService userService;
	
	@Autowired
	TacheRepository tacheRepo;
	
	@Autowired
	ProfessionRepository profRepo;
	@Autowired
	RestTemplate restTemplate;
	
	private static final String urlToAlfaSecurityApp = "utilisateur/";
	
	@GetMapping("/all")
	public List<Utilisateur> getALlUser() {
		return userService.getAllUsers();
	}
	
	@PostMapping(value="/delete/{id}", consumes="application/json", produces="application/json")
	public boolean deleteUser(@PathVariable Long id, @RequestHeader(name="Authorization")String jwtKey) {
		boolean deleteResult=false;
		Utilisateur user = userService.getUserById(id);
		if(user !=null) {
		String service ="delete/"+user.getUsername();
		// Supprimer l'utilisateur de l'autre cote
		Utilisateur userDto = new Utilisateur();
		String url = ConstantWebApi.urlToSecurityApp+ urlToAlfaSecurityApp+service;
		
		
		// Preparer les entete des requetes
		HttpHeaders header = getHeader();
		header.set("Authorization",jwtKey);

		HttpEntity<?> httpEntity= new HttpEntity<>(1,header);
		final ResponseEntity<Boolean> responseBody = restTemplate.exchange(url, HttpMethod.POST,httpEntity, Boolean.class);
		
		if(responseBody.getBody()==true) {
			 deleteResult=userService.deleteUser(id);
		}
			return deleteResult;
		}else {
			return deleteResult;
		}
	
	}
	
	@GetMapping("/find/{nom}")
	public Utilisateur getUserByName(@PathVariable String prenom){
		
		return userService.getUserByName(prenom);
		
	}
	//Recherche par username
	@GetMapping("/findUsername/{username}")
	public Utilisateur getUserByUserName(@PathVariable String username){
		
		return userService.getUserByUsername(username);
		
	}

	@GetMapping("/findUser/{id}")
	public Utilisateur getUserById(@PathVariable Long id){
		
		return userService.getUserById(id);
		
	}
	
	@PostMapping(value="/new", consumes={"application/json"})
	public Utilisateur addUser(@RequestBody Utilisateur user) {
		// preparer l'ajout simultane dans l'api de gestion de l'authentification"
		UtilisateurDto userDto = new UtilisateurDto();
		userDto.setUsername(user.getUsername());
		userDto.setNom(user.getNom());
		userDto.setPrenom(user.getPrenom());
		userDto.setActif(false);
		userDto.setTelephone(user.getTelephone());
		userDto.setEmail(user.getEmail());
		userDto.setAdresse(user.getAdresse());
		userDto.setCompany(user.getCompany());
		userDto.setPassword(user.getPassword());
		
		String service ="new";
		String url = ConstantWebApi.urlToSecurityApp + urlToAlfaSecurityApp + service;
		final Utilisateur responseBody = restTemplate.postForObject(url, userDto, Utilisateur.class);
		if(responseBody !=null) {
		 user.setPassword(responseBody.getPassword());
		Utilisateur us1=userService.addUser(user);
		if(us1 != null) {
			
			
			
		return us1;
		}
		}
		return null;//"Ajout a echoue";
	

	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable Long id,@RequestBody UtilisateurDto user, @RequestHeader(name="Authorization")String jwtKey) {
		String message = "la Modification a échouée ";
		try {	
				
				// Preparer les entete des requetes
				HttpHeaders header = getHeader();
				header.set("Authorization",jwtKey);
				Utilisateur us1 = userService.getUserById(id);
				if(us1 != null) {
					
				String service ="update/"+us1.getUsername();
				String url = ConstantWebApi.urlToSecurityApp + urlToAlfaSecurityApp + service;
				System.out.println("VOICI LA KEY JWT de la requete de la modification"+jwtKey);
				HttpEntity<?> httpEntity= new HttpEntity<>(user,header);
				final Utilisateur responseBody = restTemplate.postForObject(url, user, Utilisateur.class);
				//final ResponseEntity<Utilisateur> responseBody = restTemplate.exchange(url, HttpMethod.POST,httpEntity, Utilisateur.class);
				System.out.println("VOICI LA KEY JWT de la requete de la modification"+responseBody);
				if(responseBody != null) {
					Utilisateur returnedUser= responseBody;//.getBody();
					returnedUser.setIdUser(id);
					Utilisateur user1= userService.updateUser(returnedUser);
					System.out.println(returnedUser+"////////////////*************////////////");
					System.out.println("VOICI LA KEY JWT"+jwtKey);
					
					message ="Modification réalisé avec succées";
				}
			}
				return message;
		}catch(Exception e) {
			return e.getMessage()+"Echec";
			
		}
	}
	
	// Afficher les taches a realiser par un seul utilisateur
	@GetMapping(value="/tacheToRealise/{idUser}")
	public List<Tache> getTacheToRealiseForUser(@PathVariable Long idUser){
		List<Tache> taches = userService.TacheToRealise(idUser);
		return taches;
	}
	
	
	// Afficher les message envoyes  recu pour un utilisateur
	@GetMapping(value="/boiteEnvoi/{idUser}")
	public List<Message> getSenddeMessages(@PathVariable Long idUser){
		return userService.getAllSendedMessageFromUser(idUser);
	}
	
	// Afficher les message recu pour un utilisateur
		@GetMapping(value="/boiteReception/{idUser}")
		public List<Message> getRecivedMessages(@PathVariable Long idUser){
			return userService.getAllRecivedMessageFromUser(idUser);
		}
		
	// Appeller l api de gestion de security pour ajouter des nouvelles Statut aux utilisateurs
		
		@PostMapping(value="/accordPrivillege/{username}",consumes= {"application/json"},produces= {"application/json"})
		public ResponseEntity<?> accordNewRoleToUser(@PathVariable String username,@RequestBody List<String> permissions) {
			String service ="addPrivilleges/"+username;
			
			String url = ConstantWebApi.urlToSecurityApp+ urlToAlfaSecurityApp+service;
			final String responseBody = restTemplate.postForObject(url, permissions, String.class);
			
			return  ResponseEntity.ok(responseBody);
		
		}
		
		
		// Afficher l'entreprise dont est affecte L'utilisateur
		@GetMapping(value="/userEntreprise/{idUser}")
		public Entreprise getUserEntreprise(@PathVariable Long idUser) {
			return userService.getUserEntreprise(idUser);
		}
		
		//Afficher les Projets dont lutiliseur est concerne
		@GetMapping(value="/myProjects/{username}")
		public List<Projet> getThisUserProject(@PathVariable String username){
			// return userService.getMyProjects(username);
			return userService.getMyProjects(username);
		}
		
		// Afficher les projets dont il est membres  
		
			@GetMapping(value="/userProjects/{username}")
			public List<Projet> getProjectsOfUser(@PathVariable String username){
				
				return userService.getProjectOfUser(username);
			}
			
		// Afficher les message recu pour un utilisateur
		@GetMapping(value="/messageNonLus/{username}")
		public InfosMessageNonLu messageNonLu(@PathVariable String username){
			return userService.getMessageNonLu(username);
		}
	
		//recupere le projet, la phase et la tache de toutes les taches d'un user
		@GetMapping(value="/tasksUser/{userId}")
		public List<MonTravail> tasksUser(@PathVariable Long userId){
			return userService.tasksUser(userId);
		}
		
		@GetMapping(value="/listOfprojetEncours/{username}")
		public List<Projet> listOfprojetEncours(@PathVariable String username){
			return userService.getProjetsEncours(username);
		}
		
		@GetMapping(value="/listOfprojetEnRetard/{username}")
		public List<Projet> listOfprojetEnRetard(@PathVariable String username){
			return userService.getProjetEnretard(username);
		}

		@GetMapping(value="/listOfprojetTermines/{username}")
		public List<Projet> listOfprojetTermines(@PathVariable String username){
			return userService.getProjetTermines(username);
		}
		
	// Get Header
		public HttpHeaders getHeader() {
			HttpHeaders header = new HttpHeaders();
			return header;
		}
		
		
		
		// Etat des projets pour les administrateurs du workspace
		
		@GetMapping(value="/listOfprojetEncoursAdmin")
		public List<Projet> listOfprojetEncoursAdmin(){
			return userService.getProjetsEncoursAdmin();
		}
		
		@GetMapping(value="/listOfprojetEnRetardAdmin")
		public List<Projet> listOfprojetEnRetardAdmin(){
			return userService.getProjetEnretardAdmin();
		}

		@GetMapping(value="/listOfprojetTerminesAdmin")
		public List<Projet> listOfprojetTerminesAdmin(){
			return userService.getProjetTerminesAdmin();
		}
		

}
