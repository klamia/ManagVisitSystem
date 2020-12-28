package dz.cerist.mesrs.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dz.cerist.mesrs.entite.Visite;




@Repository("visiteDao")
@Transactional
public class VisiteDaoImpl  implements Serializable, VisiteDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887849805274386760L;

	@PersistenceContext(unitName="DbUnitPU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void persist(Visite visite) {
		// TODO Auto-generated method stub
		em.persist(visite);
	}

	@Override
	public void merge(Visite visite) {
		// TODO Auto-generated method stub
		em.merge(visite);
	}

	@Override
	public void remove(Visite visite) {
		// TODO Auto-generated method stub
		em.remove(visite);
	}

	@Override
	public void removeById(Integer id) {
		// TODO Auto-generated method stub
		Visite visite = getById(id);
		em.remove(visite);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Visite> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Visite.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visite> findVisiteByHote(String hote) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Visite.findVisiteByHote").setParameter("hote", hote).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visite> findTerminatedVisitsByHote(String hote) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Visite.findTerminatedVisitsByHote").setParameter("hote", hote).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visite> findTodayVisits() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Visite.findTodayVisits").getResultList();
	}

	@Override
	public Visite getById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Visite.class, id);
	}

	

	
}
