package dz.cerist.mesrs.dao;

import java.util.List;

import dz.cerist.mesrs.entite.Visite;



public interface VisiteDao {

	public void persist(Visite visite);

	public void merge(Visite visite);

	public void remove(Visite visite);

	public void removeById(Integer id);
	
	public List<Visite> findAll();
	
	public List<Visite> findVisiteByHote(String hote);

	public List<Visite> findTerminatedVisitsByHote(String hote) ;

	public List<Visite> findTodayVisits();
	
	public Visite getById(Integer id);

}
