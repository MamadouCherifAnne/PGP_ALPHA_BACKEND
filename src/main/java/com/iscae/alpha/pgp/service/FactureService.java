package com.iscae.alpha.pgp.service;

import com.iscae.alpha.pgp.entities.Facture;

public interface FactureService {
	public Facture addFactureOfTask(Facture facture);
	
	// Supprimer une Facture 
	public boolean deleteFacture(Long idFacture);
	

}
