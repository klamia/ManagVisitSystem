package dz.cerist.mesrs.service;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import dz.cerist.mesrs.dao.DemandeVisiteDao;
import dz.cerist.mesrs.dao.RendezVousDao;
import dz.cerist.mesrs.dao.VisiteDao;
import dz.cerist.mesrs.entite.Visite;



@Service("visiteBo")
@Transactional
public class VisiteBoImpl implements Serializable, VisiteBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4316310578438214489L;
    
	@Autowired
    private VisiteDao visiteDao;
    
	@Autowired
	private RendezVousDao rendezVousDao;
    
	@Autowired
	private DemandeVisiteDao demandeVisiteDao;
    
    
	public VisiteDao getVisiteDao() {
		return visiteDao;
	}

	public void setVisiteDao(VisiteDao visiteDao) {
		this.visiteDao = visiteDao;
	}

	public RendezVousDao getRendezVousDao() {
		return rendezVousDao;
	}

	public void setRendezVousDao(RendezVousDao rendezVousDao) {
		this.rendezVousDao = rendezVousDao;
	}

	public DemandeVisiteDao getDemandeVisiteDao() {
		return demandeVisiteDao;
	}

	public void setDemandeVisiteDao(DemandeVisiteDao demandeVisiteDao) {
		this.demandeVisiteDao = demandeVisiteDao;
	}

	@Override
	public void persist(Visite visite) {
		visiteDao.persist(visite);

	}

	@Override
	public void merge(Visite visite) {
		visiteDao.merge(visite);

	}

	@Override
	public void remove(Visite visite) {
		if (visite.getId() == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas être nul");
		}
		visiteDao.removeById(visite.getId());

	}

	@Override
	public List<Visite> findAll() {
		
		return (List<Visite>) visiteDao.findAll();
	}

	@Override
	public Visite getById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas être nul ou vide");
		}
		return visiteDao.getById(id);
	}

	
	@Override
	public void endVisitManager(Visite visite) {
		Date firstCheckout =new Date();
		visite.setFirstCheckout(new Time(firstCheckout.getTime()));
		visite.setEtat("Terminée hote");
		if (visite.getRendezVous() !=null) { 
			visite.getRendezVous().setEtat("Terminé");
		rendezVousDao.merge(visite.getRendezVous());
		}
		visiteDao.merge(visite);
		
		
			
	
	}

	@Override
	public void endVisit(Visite visite) {
		System.out.println("Ending visite");
		Date secondCheckout =new Date();
		visite.setSecondCheckout(new Time(secondCheckout.getTime()));
		visite.setEtat("Terminée");
		visiteDao.merge(visite);
		if (visite.getDemandeVisite() !=null) { 
			visite.getDemandeVisite().setEtat("Terminé");
			demandeVisiteDao.merge(visite.getDemandeVisite());
		}
	
	}

	@Override
	public void checkingVisitManager(Visite visite) {
		visite.setEtat("Validée");
		visiteDao.merge(visite);
	}

	@Override
	public void checkingVisit(Visite visite) {
		Date secondChecking =new Date();
		visite.setSecondChecking(new Time(secondChecking.getTime()));
		visite.setEtat("En Cours");
		visiteDao.merge(visite);
	}

	@Override
	public List<Visite> findVisiteByHote(String hote) {
		
        return (List<Visite>) visiteDao.findVisiteByHote(hote);
		
	}

	@Override
	public List<Visite> findTerminatedVisitsByHote(String hote) {
		// TODO Auto-generated method stub
        return (List<Visite>) visiteDao.findTerminatedVisitsByHote(hote);
	}

	@Override
	public List<Visite> findTodayVisits() {
		// TODO Auto-generated method stub
		
		return (List<Visite>) visiteDao.findTodayVisits();
	}

}
