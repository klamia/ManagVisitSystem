package dz.cerist.mesrs.service;

import java.io.Serializable;
import java.sql.Time;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.cerist.mesrs.dao.DemandeVisiteDao;
import dz.cerist.mesrs.entite.DemandeVisite;
import dz.cerist.mesrs.entite.Visite;



@Service("demandeVisiteBo")
@Transactional
public class DemandeVisteBoImpl implements Serializable, DemandeVisiteBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1261904780941551971L;
	
	
	@Autowired
	private DemandeVisiteDao demandeVisiteDao;
	
	private Visite visite;
	
	
	public DemandeVisiteDao getDemandeVisiteDao() {
		return demandeVisiteDao;
	}

	public void setDemandeVisiteDao(DemandeVisiteDao demandeVisiteDao) {
		this.demandeVisiteDao = demandeVisiteDao;
	}

	public Visite getVisite() {
		return visite;
	}

	public void setVisite(Visite visite) {
		this.visite = visite;
	}

	@Override
	public void persist(DemandeVisite demandeVisite) {
		demandeVisiteDao.persist(demandeVisite);

	}

	@Override
	public DemandeVisite merge(DemandeVisite demandeVisite) {
		return demandeVisiteDao.merge(demandeVisite);

	}

	@Override
	public void remove(DemandeVisite demandeVisite) {
		if (demandeVisite.getId() == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas �tre nul");
		}
		demandeVisiteDao.removeById(demandeVisite.getId());
	}

	@Override
	public List<DemandeVisite> findAll() {
	
		return (List<DemandeVisite>) demandeVisiteDao.findAll();
	}

	@Override
	public DemandeVisite getById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas être nul ou vide");
		}
		return demandeVisiteDao.getById(id);
	}


	@Override
	public Visite creerVisite(DemandeVisite demandeVisite) {
		// TODO Auto-generated method stub
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		visite=new Visite();
		visite.setDateVisite(sqlDate);
		//	visit.setUser(user);
		visite.setEtat("En Attente");
		visite.setFirstChecking(new Time(new java.util.Date().getTime()));
		visite.setNatureVisite("Visite Non Programmee");
	    visite.setAccompagnants(demandeVisite.getAccompagnants());
	    visite.setContact(demandeVisite.getContact());
	    visite.setDeLaPart(demandeVisite.getDeLaPart());
	    visite.setDemandeVisite(demandeVisite);  
	    visite.setFonction(demandeVisite.getFonction());
	    visite.setHote(demandeVisite.getHote());
	    visite.setMotif(demandeVisite.getMotif());
	    visite.setNomVisiteur(demandeVisite.getNomVisiteur());
	    visite.setPrenomVisiteur(demandeVisite.getPrenomVisiteur());
	    visite.setNumPiece(demandeVisite.getNumPiece());
	    visite.setTypePiece(demandeVisite.getTypePiece());
	    visite.setOrganisme(demandeVisite.getOrganisme());
	    visite.setPhone(demandeVisite.getPhone());
	    return visite;
	    
	
	}

	@Override
	public DemandeVisite voirDemande(DemandeVisite demandeVisite) {
		
		
		return demandeVisite;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accepterDemande(DemandeVisite demandeVisite) {
		// TODO Auto-generated method stub
		demandeVisite.setEtat("Acceptée");
		demandeVisiteDao.merge(demandeVisite);
	
	}

	@Override
	public void rejeterDemande(DemandeVisite demandeVisite) {
		// TODO Auto-generated method stub
		demandeVisite.setEtat("Rejetée");
		demandeVisiteDao.merge(demandeVisite);
	
	}

	@Override
	public List<DemandeVisite> findByHote(String hote) {
		
        return (List<DemandeVisite>) demandeVisiteDao.findByHote(hote);
	}

	@Override
	public List<DemandeVisite> findTerminatedRequestByHote(String hote) {
		
        return (List<DemandeVisite>) demandeVisiteDao.findTerminatedRequestByHote(hote);
		
		
	}
	
	@Override
	public List<DemandeVisite> findTodayRequests() {
		// TODO Auto-generated method stub
		
		
		return (List<DemandeVisite>) demandeVisiteDao.findTodayRequests();
		
	}

	

}
