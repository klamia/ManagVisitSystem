package dz.cerist.mesrs.service;

import java.util.List;

import dz.cerist.mesrs.entite.DemandeVisite;
import dz.cerist.mesrs.entite.Visite;




public interface DemandeVisiteBo {
	
	public void persist(DemandeVisite demandeVisite);

	public DemandeVisite merge(DemandeVisite demandeVisite);

	public void remove(DemandeVisite demandeVisite);

	public List<DemandeVisite> findAll();
	
	public List<DemandeVisite> findByHote(String hote);
	
	public List<DemandeVisite> findTerminatedRequestByHote(String hote) ;

	public List<DemandeVisite> findTodayRequests();
	
	public DemandeVisite getById(Integer id);
	
	public void accepterDemande(DemandeVisite demandeVisite);
	
	public void rejeterDemande(DemandeVisite demandeVisite);
	
	public Visite creerVisite(DemandeVisite demandeVisite);
	
	public DemandeVisite voirDemande(DemandeVisite demandeVisite);
	
	

}
