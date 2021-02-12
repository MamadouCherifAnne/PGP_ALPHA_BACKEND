package com.iscae.alpha.pgp.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.ProjectUtilisateursRepository;
import com.iscae.alpha.pgp.dao.ProjetRepository;
import com.iscae.alpha.pgp.dto.MembreProjetDto;
import com.iscae.alpha.pgp.entities.Commentaire;
import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.entities.ProjectUserID;
import com.iscae.alpha.pgp.entities.ProjectUtilisateurs;
import com.iscae.alpha.pgp.entities.Projet;
import com.iscae.alpha.pgp.entities.Tache;
import com.iscae.alpha.pgp.entities.Utilisateur;


@Service 

public class ProjetServiceImp implements ProjetService{
	
	@Autowired
	ProjetRepository projetRepository;
	@Autowired 
	CommentaireService commentService;
	@Autowired
	UtilisateurService userService;
	@Autowired
	ProjectUtilisateursRepository projetMembreRepo;
	
	@Autowired
	TacheService tacheService;
	
	private final static Logger log= LoggerFactory.getLogger(ProjetServiceImp.class);
	
	@Override
	public Projet AddProjet(Projet projet) {
		Projet project = projetRepository.save(projet);
		ProjectUtilisateurs membre = new ProjectUtilisateurs();
		ProjectUserID  idMembre = new ProjectUserID();
		idMembre.setIdProjet(project.getNumProjet());
		idMembre.setIdUser(project.getResponsable().getIdUser());
		membre.setIdMembre(idMembre);
		membre.setRole("responsable");
		this.addMembreToProject(membre);
		return null;
	}

	@Override

	public boolean updateProjet(Projet projet) {

		Optional<Projet> projet1 = projetRepository.findById(projet.getNumProjet());
		
		if(projet1 != null) {
			Projet projet2 = projet1.get();
			projet2.setNumProjet(projet.getNumProjet());
			projet2.setNomProjet(projet.getNomProjet());
			projet2.setDebutProjet(projet.getDebutProjet());
			
			projet2.setDescription(projet.getDescription());
			projet2.setZoneRealisation(projet.getZoneRealisation());
			projet2.setFinProjet(projet.getFinProjet());
			projet2.setPhases(projet.getPhases());
			projet2.setResponsable(projet.getResponsable());
			projet2.setRisques(projet.getRisques());
			
			projetRepository.save(projet2);

			return true;
		}
		return false;

	}

	@Override
	public void deleateProjet(Long projetId) {
		Projet projet = projetRepository.findById(projetId).get();
		projetRepository.delete(projet);
	}

	@Override
	public Projet findProjetById(Long projetId) {
		
		//Verification de l'existance d'un projet si oui on le retourne sinon on retourne null;
		if (projetRepository.findById(projetId).isPresent()) {
			Projet projet = projetRepository.findById(projetId).get();
			
			return projet;
		}
		
		return null;
	}

	@Override
	public List<Projet> findAllProjets() {
		
		return projetRepository.findAll();
	}

	@Override
	public Projet findByDateDebut(Date dateDebut) {
		Projet projet = projetRepository.findByDebutProjet(dateDebut);
		return projet;
	}

	@Override
	public Projet importer(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  Projet export(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//exportation vers Excel
	public  ByteArrayInputStream exportProjet(Projet projet) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Projet");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Nom Tache");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Durée");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Charge Tache");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Niveau priorite");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Taux d'avancement");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Statut");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(6);
	        cell.setCellValue("Nom Phase");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(7);
	        cell.setCellValue("Date debut");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(8);
	        cell.setCellValue("Date fin");
	        cell.setCellStyle(headerCellStyle);
	        
	      
	     // Creating data rows for each customer
	        List<Tache> taches = projectTasks(projet.getNumProjet());
	       for(int i = 0; i < taches.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(taches.get(i).getNomTache());
	        	dataRow.createCell(1).setCellValue(taches.get(i).getDuree());
	        	dataRow.createCell(2).setCellValue(taches.get(i).getChargeTache());
	        	dataRow.createCell(3).setCellValue(taches.get(i).getNiveauPriorite());
	        	dataRow.createCell(4).setCellValue(taches.get(i).getTauxAvancement());
	        	
	        	dataRow.createCell(5).setCellValue(tacheStatut(taches.get(i)));
	      
	        	dataRow.createCell(6).setCellValue(taches.get(i).getPhase().getNomTache());
	        	dataRow.createCell(7).setCellValue(taches.get(i).getDebutTache().toString());
	        	dataRow.createCell(8).setCellValue(taches.get(i).getFinTache().toString());
	        } 
	        
	        
	        
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        sheet.autoSizeColumn(7);
	        sheet.autoSizeColumn(8);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public String tacheStatut(Tache tache) {
		String statut = "";
		Date now = new Date();	 
		if(tache.getTauxAvancement() == 100 && isFinished(tache) == 0) { statut = "Termée";}
		 if(tache.getFinTache().before(now) && tache.getTauxAvancement() != 100) { statut = "En retard";}
		 if(tache.getDebutTache().before(now) && tache.getFinTache().after(now) &&tache.getTauxAvancement() != 100) {
			 statut = "En cours";
		 }
		 
		 return statut;
	}
	
	public int isFinished(Tache tache){
	    int find = 0;
	    if(tache.getTachePrecedente() != null){
	      for(Tache itache : tache.getTachePrecedente()){
	        if(itache.getTauxAvancement() != 100){
	          find = 1;
	          break;
	        }
	      }
	    }
	    System.out.println("-------------"+find);
	    return find;
	  }
	

	@Override
	public Projet findByDateFin(Date dateFin) {
		Projet projet = projetRepository.findByFinProjet(dateFin);
		return projet;
	}

	@Override
	public List<Phase> listPhaseProjet(Long idProjet) {
		Projet projet = projetRepository.findById(idProjet).get();
		return projet.getPhases();
	}

	@Override
	public List<Tache> projectTasks(Long numProjet) {
		// TODO Auto-generated method stub
		if(this.findProjetById(numProjet)!=null) {
			Projet p = this.findProjetById(numProjet);
			List<Tache> taches = new ArrayList<>();
			for(Phase ph:p.getPhases()) {
				for(Tache tache:ph.getTache()) {
					if(tache.getType()==null || !tache.getType().equals("Jalon") ) {
						taches.add(tache);
					}
				}
			}
			
		return taches;
		}
		return new ArrayList<>();
	}

	@Override
	public List<Tache> getAllJalons(Long numProjet) {
		// TODO Auto-generated method stub
		return projetRepository.allProjectJalons(numProjet);
	}

	@Override
	public List<Tache> getRetardJalon(Long numProjet) {
		// TODO Auto-generated method stub
		List<Tache> jalons = new ArrayList<>();
		jalons = this.getAllJalons(numProjet);
		return null;
	}

	@Override
	public Projet addcommentsToProject(List<Commentaire> comments) {
		// Ajout de plusieur commentaire a meme temps
		if(!comments.isEmpty()) {
			Projet projet=new Projet();
			for(Commentaire com : comments) {
				Commentaire coment = commentService.addComment(com);
				projet = coment.getProjetComment();
			}
			return projet;
		}
		return null;
	}

	@Override
	public List<Commentaire> allCommentsOfProject(Long idProjet) {
		// Afficher la liste des commentaires d'un projet
		List<Commentaire> comments = new ArrayList<>();
		Projet projet =findProjetById(idProjet);
		if(projet != null) {
			
			comments = projet.getCommentaires();
			Collections.sort(comments, (y, x) -> x.getDateComment().compareTo(y.getDateComment()));
			return comments ;
		}
		
		return comments;
	}

	@Override
	public boolean addMembreToProject(ProjectUtilisateurs membreProjet) {
		// TODO Auto-generated method stub
		ProjectUtilisateurs projetMembre = new ProjectUtilisateurs();
		ProjectUserID projectMembreId = new ProjectUserID();
		
		if(membreProjet.getIdMembre().getIdProjet() != null && membreProjet.getIdMembre().getIdUser() != null) {
			projetMembreRepo.save(membreProjet);
			return true;
		}
		
		return false;
	}

	@Override
	public Collection<MembreProjetDto> getMembreOfProject(Long id) {
		// TODO Auto-generated method stub
		Collection<MembreProjetDto> membres = new ArrayList<>();
		List<ProjectUtilisateurs> projetMembres = new ArrayList<>();
		projetMembres = projetMembreRepo.getProjectsMembres(id);
		if(projetMembres != null) {
				for(ProjectUtilisateurs projetMembre : projetMembres ) {
					Utilisateur user = userService.getUserById(projetMembre.getIdMembre().getIdUser());
					MembreProjetDto userDto = new MembreProjetDto();
					
					userDto.setIdUser(user.getIdUser());
					userDto.setUsername(user.getUsername());
					userDto.setNom(user.getNom());
					userDto.setPrenom(user.getPrenom());
					userDto.setActif(false);
					userDto.setTelephone(user.getTelephone());
					userDto.setEmail(user.getEmail());
					userDto.setAdresse(user.getAdresse());
					userDto.setCompany(user.getCompany());
					userDto.setProjectRole(projetMembre.getRole());
					membres.add(userDto);
				}
				
			}
		
			return membres;
		}

	@Override
	public int verifRoleMembre(Long idProject, Long idUser) {
		//  Verification du role d'un utilisateur  sur un projet
		int role=-1;
		Collection<MembreProjetDto> membresProject= this.getMembreOfProject(idProject);
		if(membresProject != null && membresProject.size() > 0) {
			for(MembreProjetDto membre : membresProject) {
				if(membre.getIdUser() == idUser && membre.getProjectRole() != null) {
					if(membre.getProjectRole().equalsIgnoreCase("collaborateur") || membre.getProjectRole().equalsIgnoreCase("acteur")) {
					role =1;
					break;
					}
					else if( membre.getProjectRole().equalsIgnoreCase("responsable")){
						role =2;
						break;
					}
				}
			}
		}
		return role;
		
	}

	@Override
	public boolean deleteMembreOfProject(ProjectUserID idMembre) {
		boolean reponse = false;
		boolean isAffectedToTach = false;
		// suppression d'un membre dans le projet
		Utilisateur user= userService.getUserById(idMembre.getIdUser());
		if(user != null) {
			List<Projet> projectsUser = userService.getMyProjects(user.getUsername());
			if(projectsUser != null && !projectsUser.isEmpty()) {
				for (Projet projet : projectsUser) {
					if(projet.getNumProjet() == idMembre.getIdProjet()) {
						isAffectedToTach = true;
						break;
					}
				}
			}
			// Verifier si l'utilisateur  n'est pas lier a une tache dans ce projet
			if(isAffectedToTach == false) {
				projetMembreRepo.deleteById(idMembre);
				reponse = true;
			}
		}
		return reponse;
		
	}

	@Override
	public Utilisateur getProjectOwner(Long IdProject) {
		// Afficher le chef du projet 
		Projet projet = this.findProjetById(IdProject);
		if(projet != null) {
			return projet.getResponsable();
		}else {
			return null;
		}
	}

	@Override
	public double coutTotalProject(Long idProject) {
		double totale=0;
		List<Tache> projectTsaks = new ArrayList<>();
		// Calcul de la somme des cout de chaque taches
		Projet projet = this.findProjetById(idProject);
		if(projet != null) {
			projectTsaks= this.projectTasks(idProject);
			for (Tache tache : projectTsaks) {
				totale = totale + tacheService.totalCoutTask(tache.getNumTache());
			}
		}
		return totale;
	}

	@Override
	public double coutProjectLastMonth(Long idProject) {
		// Afficher le cout du Projet pendant 6 mois
		Date limiteDate = new Date(System.currentTimeMillis()-24*60*60*1000*20);
		Date today = new Date(System.currentTimeMillis());
		log.info("La date d'hier"+limiteDate+" La date de today is"+today);
		long timer =(today.getTime()-limiteDate.getTime())/(1000 *3600*24);
		System.out.println("VOICI LA DATE DE 6 MOIS AVANT ::::#####"+timer);
		return 0;
	}

	public int getMyProjectsActifs(String username) {
		// le projet est un cours si la dalai n'est pas encore depassé et qu'il reste des taches en cours
 		List<Projet> projets = userService.getMyProjects(username);
		int cmpt = 0;
		 Date now = new Date();
		if(projets != null) {
			for(Projet projet: projets) {
				if(projet.getFinProjet().after(now) && verifTacheNotfinish(projet.getNumProjet()) == 1) {
					cmpt = cmpt + 1;
				}
			}
		}
		return cmpt;
	}

	public int verifTacheNotfinish(Long numProjet) {
		int val = 0;
		Date now = new Date();
		List<Tache> tasks = projectTasks(numProjet);
		if(tasks != null) {
			for(Tache tache: tasks) {
				if(tache.getTauxAvancement() != 100) {
					val = val + 1;
					break;
				}
			}
		}
		return val;
	}
	
	//............................................................
	/////////////////////////////////////////////////////////
	@Override
	public int getMyProjectsEnretards(String username) {
		// le projet est en retard si le delai est depassé et quil reste des taches en cours
		List<Projet> projets = userService.getMyProjects(username);
		int cmpt = 0;
		 Date now = new Date();
		if(projets != null) {
			for(Projet projet: projets) {
				if(projet.getFinProjet().before(now) && verifTacheNotfinish(projet.getNumProjet()) == 1) {
					cmpt = cmpt + 1;
				}
			}
		}
		return cmpt;
	}

	@Override
	public int getMyProjectsTermines(String username) {
		// TODO Auto-generated method stub
		List<Projet> projets = userService.getMyProjects(username);
		int cmpt = 0;
		 Date now = new Date();
		 if(projets != null) {
				for(Projet projet: projets) {
					if(verifTacheNotfinish(projet.getNumProjet()) == 0) {
						cmpt = cmpt + 1;
					}
				}
		}
		return cmpt;

	}
	


}
