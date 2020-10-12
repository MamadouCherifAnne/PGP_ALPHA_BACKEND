package com.iscae.alpha.pgp.service;

import com.iscae.alpha.pgp.entities.DataSourceConfig;

public interface MasterTenantService {
	
	public DataSourceConfig getByTenantId(String tenant);
	
	// Ajouter un nouveau client
	public DataSourceConfig addNewLocataire(String tenantName, String url,String driverClass,String username,String password);

}
