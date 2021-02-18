package com.iscae.alpha.pgp.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.Phase;
import com.iscae.alpha.pgp.service.RapportProjectServiceImp;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/rapport")
@CrossOrigin(origins="*")
public class RapportProjectController {

	@Autowired
	private RapportProjectServiceImp rapportService;
	private Path fileStorageLocation;
	
	private static final Logger log = LoggerFactory.getLogger(RapportProjectController.class);
	
    
	
	
	@GetMapping("/projet/{projetId}")
	public String rapportProjet(@PathVariable Long projetId) throws JRException {
		System.out.println("point d'entré");
		try {
			System.out.println("point de repere1");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportService.rapportProjet1(projetId));
			System.out.println("point de repere2");
			File file = ResourceUtils.getFile("classpath:lerepport.jrxml");
			System.out.println("point de repere3");
			JasperReport jasperRaport = JasperCompileManager.compileReport(file.getAbsolutePath());
			System.out.println("point de repere 4");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperRaport, null, dataSource);
			System.out.println("point de repere vv");
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Oumar BA\\Desktop\\Rapports"+"\\rapportprojet.pdf");
			System.out.println("point de repere v8");
			return "rapport généré dans"+"C:\\\\Users\\\\Oumar BA\\\\Desktop\\\\Rapports";
		}catch(Exception e) {
			return "ya un problem";
		}
		
		
	}
	
	
	
	
	///////////////////////////////////////
	//@PostMapping("/export/{projetId}")
	@GetMapping("/export/{projetId}")
	public void exportDailyOrders(@PathVariable  Long projetId, HttpServletResponse httpServletResponse) throws IOException, JRException {
	    byte[] dailyOrdersBytes = exportDailyOrders(projetId);
	    log.info("Fin DU CHARGEMENT DES TACHES DU PROJET PAR EXPORTDAILY");
	    ByteArrayOutputStream out = new ByteArrayOutputStream(dailyOrdersBytes.length);
	    out.write(dailyOrdersBytes, 0, dailyOrdersBytes.length);
	    log.info("METTRE LE TYPE DE FICHIER EN PDF");
	    httpServletResponse.setContentType("application/pdf");
	    httpServletResponse.addHeader("Content-Disposition", "inline; filename=dailyOrdersReport.pdf");

	    OutputStream os;
	    try {
	    	log.info("CONFIGURATION DU SYSTEME DE FICHIER OUTPUTSTREAM");
	        os = httpServletResponse.getOutputStream();
	        out.writeTo(os);
	        os.flush();
	        os.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	public byte[] exportDailyOrders(Long projetId) throws IOException, JRException {
	    
		log.info("ENTRER DANS LA METHODE DE EXPORTDAILY DE RAPPORT");
	    File file = ResourceUtils.getFile("classpath:lerepport.jrxml");
	    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	    log.info("RECUPERATION DES TACHES DU PROJET RAPPORTSERVICE");
	    final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportService.rapportProjet1(projetId));
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("createdBy", "Nikola");
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	    ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);
	    return byteArrayOutputStream.toByteArray();
	}

	protected ByteArrayOutputStream getByteArrayOutputStream(JasperPrint jasperPrint) throws JRException {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
	    return byteArrayOutputStream;
	}
	
	
	///////////////////////////////////////
	@GetMapping("/exporttest/{idprojet}")
	public List<Phase> exportPhase(@PathVariable Long idprojet) throws FileNotFoundException, JRException {
		return rapportService.exportPhase(idprojet);
	}
 
	
}
