package com.iscae.alpha.pgp.dto;

import com.iscae.alpha.pgp.entities.DataSourceConfig;
import com.iscae.alpha.pgp.entities.Utilisateur;

public class TenantCompteDto {

	private Utilisateur proprietaire;
	private DataSourceConfig tenantCompte;
	public TenantCompteDto(Utilisateur proprietaire, DataSourceConfig tenantCompte) {
		super();
		this.proprietaire = proprietaire;
		this.tenantCompte = tenantCompte;
	}
	public TenantCompteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Utilisateur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}
	public DataSourceConfig getTenantCompte() {
		return tenantCompte;
	}
	public void setTenantCompte(DataSourceConfig tenantCompte) {
		this.tenantCompte = tenantCompte;
	}
	
	
}
