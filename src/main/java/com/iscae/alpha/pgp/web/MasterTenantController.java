package com.iscae.alpha.pgp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iscae.alpha.pgp.entities.DataSourceConfig;
import com.iscae.alpha.pgp.service.MasterTenantService;


@RestController
@RequestMapping("/locataire")
@CrossOrigin(origins="*")
public class MasterTenantController {
	@Autowired
	MasterTenantService masterTenantservice;
	
	@PostMapping(value = "/newLocataire")
	public String addNewTenant(@RequestBody DataSourceConfig tenantDS) {
		DataSourceConfig ds = null;
		ds =masterTenantservice.addNewLocataire(tenantDS.getName(), tenantDS.getUrl(), tenantDS.getDriverClassName(), 
				tenantDS.getUrl(), tenantDS.getPassword());
		if(ds == null) {
			return "Le compte n'a pas pu etre creer verifier le service CLient";
			
		}
		return "Zone de Location a été creer avec succes contacter votre fournisseur";
	}
	
}
