package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import dz.cerist.mesrs.entite.DemandeVisite;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.DemandeVisiteBo;
import dz.cerist.mesrs.service.UserBo;

@ManagedBean
@ViewScoped
public class NewRequestView implements Serializable {

	private static final long serialVersionUID = -1179722425157936101L;

	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;
	
	@ManagedProperty(name="demandeVisiteBo", value="#{demandeVisiteBo}")
	private DemandeVisiteBo demandeVisiteBo ;
	
	@ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;

	
	private DemandeVisite demVisit;

	public DemandeVisite getDemVisit() {
		return demVisit;
	}

	public void setDemVisit(DemandeVisite demVisit) {
		this.demVisit = demVisit;
	}

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	public DemandeVisiteBo getDemandeVisiteBo() {
		return demandeVisiteBo;
	}

	public void setDemandeVisiteBo(DemandeVisiteBo demandeVisiteBo) {
		this.demandeVisiteBo = demandeVisiteBo;
	}

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	@PostConstruct
	public void init(){
		demVisit=new DemandeVisite();
		
	}

	public String saveRequestVisit() {	
		User user=userBo.getByLogin(currentUser.getLogin());
		demVisit.setEtat("En Attente");
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		demVisit.setDateDemande(sqlDate);
		demVisit.setUser(user);
		demandeVisiteBo.persist(demVisit);
		return "visitRequests";
	}
	
	
}
