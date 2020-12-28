package dz.cerist.mesrs.service;

import java.io.Serializable;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.cerist.mesrs.dao.RendezVousDao;
import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;


@Service("rendezVousBo")
@Transactional
public class RendezVousBoImpl implements RendezVousBo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1593558604274212415L;

	@Autowired
	private RendezVousDao rendezVousDao;

	public RendezVousDao getRendezVousDao() {
		return rendezVousDao;
	}

	public void setRendezVousDao(RendezVousDao rendezVousDao) {
		this.rendezVousDao = rendezVousDao;
	}

	@Override
	public void persist(RendezVous rendezVous) {
		rendezVousDao.persist(rendezVous);

	}

	@Override
	public RendezVous merge(RendezVous rendezVous) {
		return rendezVousDao.merge(rendezVous);

	}

	@Override
	public void remove(RendezVous rendezVous) {
		if (rendezVous.getId() == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas être nul ");
		}
		rendezVousDao.removeById(rendezVous.getId());

	}

	
	@Override
	public List<RendezVous> findAll() {

		return (List<RendezVous>) rendezVousDao.findAll();
	}

	@Override
	public RendezVous getById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas être nul ou vide");
		}
		return rendezVousDao.getById(id);
	}

	

	@Override
	public void RendreVisible(RendezVous rendezVous) {
		// TODO Auto-generated method stub
			rendezVous.setVisibilite(true);
			rendezVousDao.merge(rendezVous);		
	}

	// Liste des RDV en attente et en cours pour les responsables
	@Override
	public List<RendezVous> findByUser(User user) {
		
        return (List<RendezVous>) rendezVousDao.findByUser(user);
	}

	// Liste des RDV termin�s pour les responsables
		
	@Override
		public List<RendezVous> findTerminatedRDVByUser(User user) {
			
	        return (List<RendezVous>) rendezVousDao.findTerminatedRDVByUser(user);
		}
	
	
	// Liste des RDV pour les agents et les receptionists
	@Override
	public List<RendezVous> findTodayRDV() {
		// TODO Auto-generated method stub
		
		return (List<RendezVous>) rendezVousDao.findTodayRDV();
	}

	
}
