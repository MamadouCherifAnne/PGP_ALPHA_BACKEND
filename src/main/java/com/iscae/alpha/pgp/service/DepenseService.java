package com.iscae.alpha.pgp.service;

import java.util.Date;
import java.util.List;

import com.iscae.alpha.pgp.entities.Depense;

public interface DepenseService {
	// Ajouter une depense
	public Depense addDepense(Depense depense);
	
	// Modifier Une depense
	
	//Supprimer une depense
	public boolean deleteDepense(Long idDepense);
	
	//chercher une depesne par lidentifiant
	public Depense findDepenseById(Long idDepense);
	
	//chercher depense par la date d'enregistrement de la depense
	public List<Depense> getDepenseByDate(Date dateSauvegarde);

}
