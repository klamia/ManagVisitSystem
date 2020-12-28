package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.RendezVousBo;
import dz.cerist.mesrs.service.UserBo;


@ManagedBean
@ViewScoped
public class NewRdvView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(name="rendezVousBo", value="#{rendezVousBo}")
	private RendezVousBo rendezVousBo;
	
	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;
	
	
	@ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	
	private RendezVous rdv;
	
	private Date rdvTime;
	
	public RendezVousBo getRendezVousBo() {
		return rendezVousBo;
	}

	public void setRendezVousBo(RendezVousBo rendezVousBo) {
		this.rendezVousBo = rendezVousBo;
	}

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	@PostConstruct
	public void init(){
		rdv=new RendezVous();
		rdv.setEtat("En Attente");
		rdv.setHasVisit(false);
		System.out.println("Ouvrir la vue NewRdv");
	}
	
	public String saveRdv() {
		System.out.println("1- Heure RDV est "+rdv.getHeureRdv());
		rdv.setHeureRdv(new Time (rdvTime.getTime()));
		User user=userBo.getByLogin(currentUser.getLogin());
		rdv.setUser(user);
		rdv.setHasVisit(false);
		rendezVousBo.persist(rdv);
		return "myRDV";
	}



	public RendezVous getRdv() {
		return rdv;
	}

	public void setRdv(RendezVous rdv) {
		this.rdv = rdv;
	}

	public Date getRdvTime() {
		return rdvTime;
	}

	public void setRdvTime(Date rdvTime) {
		this.rdvTime = rdvTime;
	}
	
	

}
