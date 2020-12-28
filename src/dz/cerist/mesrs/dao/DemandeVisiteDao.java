package dz.cerist.mesrs.dao;

import java.util.List;

import dz.cerist.mesrs.entite.DemandeVisite;


public interface DemandeVisiteDao {

	public void persist(DemandeVisite demandeVisite);

	public DemandeVisite merge(DemandeVisite demandeVisite);

	public void remove(DemandeVisite demandeVisite);

	public void removeById(Integer id);
	
	public List<DemandeVisite> findAll();
	
	public DemandeVisite getById(Integer id);
	
	public List<DemandeVisite> findByHote(String hote);
	
	public List<DemandeVisite> findTerminatedRequestByHote(String hote) ;

	public List<DemandeVisite> findTodayRequests();

}
