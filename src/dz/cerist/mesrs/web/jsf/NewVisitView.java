package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.sql.Time;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.entite.Visite;
import dz.cerist.mesrs.service.RendezVousBo;
import dz.cerist.mesrs.service.UserBo;
import dz.cerist.mesrs.service.VisiteBo;


@ManagedBean
@ViewScoped
public class NewVisitView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(name="visiteBo", value="#{visiteBo}")
	private VisiteBo visiteBo;
	
	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;
	
	@ManagedProperty(name="rendezVousBo", value="#{rendezVousBo}")
	private RendezVousBo rendezVousBo;
	
	@ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	

	private  RendezVous rdv;
		
	private Visite visit;

    
	public Visite getVisit() {
		return visit;
	}


	public void setVisit(Visite visit) {
		this.visit = visit;
	}
	
	
	public RendezVous getRdv() {
		return rdv;
	}


	public void setRdv(RendezVous rdv) {
		this.rdv = rdv;
	}

	public VisiteBo getVisiteBo() {
		return visiteBo;
	}


	public void setVisiteBo(VisiteBo visiteBo) {
		this.visiteBo = visiteBo;
	}


	public UserBo getUserBo() {
		return userBo;
	}


	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}


	public RendezVousBo getRendezVousBo() {
		return rendezVousBo;
	}


	public void setRendezVousBo(RendezVousBo rendezVousBo) {
		this.rendezVousBo = rendezVousBo;
	}


	public CurrentUser getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}


	public void init(String id){
		visit=new Visite();
		rdv=rendezVousBo.getById(Integer.valueOf(id));
	}
	
	public String saveVisit(String outcome,String id){
		
		
		User user=userBo.getByLogin(currentUser.getLogin());
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		visit.setDateVisite(sqlDate);
		visit.setUser(user);
		visit.setEtat("En Attente");
		visit.setFirstChecking(new Time(new java.util.Date().getTime()));
		visit.setNatureVisite("Visite Programmee");
		visit.setRendezVous(rdv);
		visit.setNomVisiteur(rdv.getNomVisiteur());
		visit.setPrenomVisiteur(rdv.getPrenomVisiteur());
		visiteBo.persist(visit);
		rdv.setEtat("En Cours");
		rdv.setHasVisit(true);
		rdv.setVisite(visit);
		rendezVousBo.merge(rdv);
		return outcome;
	}
	
	
}
