package com.iscae.alpha.pgp.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.service.RapportProjectServiceImp;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@RestController
@RequestMapping("/rapport")
@CrossOrigin(origins="*")
public class RapportProjectController {

	@Autowired
	private RapportProjectServiceImp rapportService;
	private Path fileStorageLocation;
	
    
	@GetMapping("/projet/{projetId}")
	public String rapportProjet(@PathVariable Long projetId) throws JRException {
		System.out.println("point d'entré");
		try {
			System.out.println("point de repere1");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportService.reportProjet(projetId));
			System.out.println("point de repere2");
			File file = ResourceUtils.getFile("classpath:rapportprotect.jrxml");
			System.out.println("point de repere3");
			JasperReport jasperRaport = JasperCompileManager.compileReport(file.getAbsolutePath());
			System.out.println("point de repere 4");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperRaport, null, dataSource);
			System.out.println("point de repere vv");
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Oumar BA\\Desktop\\Rapports"+"\\rapportprojet.pdf");
			
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
	    ByteArrayOutputStream out = new ByteArrayOutputStream(dailyOrdersBytes.length);
	    out.write(dailyOrdersBytes, 0, dailyOrdersBytes.length);

	    httpServletResponse.setContentType("application/pdf");
	    httpServletResponse.addHeader("Content-Disposition", "inline; filename=dailyOrdersReport.pdf");

	    OutputStream os;
	    try {
	        os = httpServletResponse.getOutputStream();
	        out.writeTo(os);
	        os.flush();
	        os.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	public byte[] exportDailyOrders(Long projetId) throws IOException, JRException {
	    
	    File file = ResourceUtils.getFile("classpath:rapportprotect.jrxml");
	    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	    final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportService.reportProjet(projetId));
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

	
}
