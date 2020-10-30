package com.iscae.alpha.pgp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iscae.alpha.pgp.dao.DataSourceConfigRepository;
import com.iscae.alpha.pgp.entities.DataSourceConfig;
@Service
public class MasterTenantServiceImpl  implements MasterTenantService{
	
	@Autowired
	DataSourceConfigRepository dsCOnfigRepo;
	
	@Override
	public DataSourceConfig getByTenantId(String tenant) {
		// TODO Auto-generated method stub
		DataSourceConfig dataSource =dsCOnfigRepo.findByName(tenant);
		if(dataSource!=null) {
			return dataSource;
		}
		
		return null;
	}
	@Override
	public DataSourceConfig addNewLocataire(String tenantName, String url,String driverClass,String username,String password) {
		// TODO Auto-generated method stub
		DataSourceConfig newTenant = new DataSourceConfig();
		DataSourceConfig tenantDb =this.getByTenantId(tenantName);
		if(tenantDb == null) {
			newTenant.setUsername("root");
			newTenant.setDriverClassName("com.mysql.cj.jdbc.Driver");
			newTenant.setName(tenantName);
			newTenant.setPassword("");
			newTenant.setInitialize(true);
			return dsCOnfigRepo.save(newTenant);
		}
		
		
		return null;
	}

}
