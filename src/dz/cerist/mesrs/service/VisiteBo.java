package dz.cerist.mesrs.service;

import java.util.List;

import dz.cerist.mesrs.entite.Visite;




public interface VisiteBo {

	public void persist(Visite viste);

	public void merge(Visite viste);

	public void remove(Visite viste);

	public List<Visite> findAll();
	
	public List<Visite> findVisiteByHote(String hote);

	public List<Visite> findTerminatedVisitsByHote(String hote) ;

	public List<Visite> findTodayVisits();
	
	public Visite getById(Integer id);
	
	public void endVisitManager(Visite visite);
	
	public void endVisit(Visite visite);
	
	public void checkingVisitManager(Visite visite);
	
	public void checkingVisit(Visite visite);
	
}
