package dz.cerist.mesrs.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;



@Repository("rendezVousDao")
@Transactional
public class RendezVousDaoImpl  implements Serializable, RendezVousDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1830998817504474813L;

	@PersistenceContext(unitName="DbUnitPU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public void persist(RendezVous rendezVous) {
		// TODO Auto-generated method stub
		em.persist(rendezVous);
	}

	@Override
	public RendezVous merge(RendezVous rendezVous) {
		
		return em.merge(rendezVous);
			
	}

	@Override
	public void remove(RendezVous rendezVous) {
		// TODO Auto-generated method stub
		em.remove(rendezVous);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RendezVous> findAll() {
		// TODO Auto-generated method stub
		return  em.createNamedQuery("RendezVous.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RendezVous> findByUser(User user) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("RendezVous.findByUser").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RendezVous> findTerminatedRDVByUser(User user) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("RendezVous.findTerminatedRDVByUser").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RendezVous> findTodayRDV() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("RendezVous.findTodayRDV").getResultList();
	}

	@Override
	public RendezVous getById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(RendezVous.class, id);
	}

	@Override
	public void removeById(Integer id) {
		// TODO Auto-generated method stub
		RendezVous rendezVous = getById(id);
		em.remove(rendezVous);
	}

	
	
	
}
