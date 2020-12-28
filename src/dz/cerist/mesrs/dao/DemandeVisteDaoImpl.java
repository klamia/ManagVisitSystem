package dz.cerist.mesrs.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.cerist.mesrs.entite.DemandeVisite;




@Repository("demandeVisiteDao")
@Transactional
public class DemandeVisteDaoImpl  implements Serializable, DemandeVisiteDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8739811325347566508L;

	
	@PersistenceContext(unitName="DbUnitPU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void persist(DemandeVisite demandeVisite) {
		// TODO Auto-generated method stub
		em.persist(demandeVisite);
	}

	@Override
	public DemandeVisite  merge(DemandeVisite demandeVisite) {
		
		return em.merge(demandeVisite);
		
		
	}

	@Override
	public void remove(DemandeVisite demandeVisite) {
		// TODO Auto-generated method stub
		em.remove(demandeVisite);
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DemandeVisite> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("DemandeVisite.findAll").getResultList();
	}

	@Override
	public DemandeVisite getById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(DemandeVisite.class, id);
	}

	@Override
	public void removeById(Integer id) {
		// TODO Auto-generated method stub
		DemandeVisite demandeVisite = getById(id);
		em.remove(demandeVisite);
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<DemandeVisite> findByHote(String hote) {
		// TODO Auto-generated method stub
		return  em.createNamedQuery("DemandeVisite.findByHote").setParameter("hote", hote).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DemandeVisite> findTerminatedRequestByHote(String hote) {
		// TODO Auto-generated method stub
		return  em.createNamedQuery("DemandeVisite.findTerminatedRequestByHote").setParameter("hote", hote).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DemandeVisite> findTodayRequests() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("DemandeVisite.findTodayRequests").getResultList();
	}

	
}
