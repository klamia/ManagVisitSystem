package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.entite.Visite;
import dz.cerist.mesrs.service.UserBo;
import dz.cerist.mesrs.service.VisiteBo;

@ManagedBean
@RequestScoped
public class ListVisitsView implements Serializable {
	
	private static final long serialVersionUID = 2803478405877742044L;

private List<Visite> allTerminatedVisits;
	
    @ManagedProperty(name="visiteBo", value="#{visiteBo}")
	private VisiteBo visiteBo;
	
    @ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	
    @ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;

	
	public VisiteBo getVisiteBo() {
		return visiteBo;
	}


	public void setVisiteBo(VisiteBo visiteBo) {
		this.visiteBo = visiteBo;
	}


	public CurrentUser getCurrentUser() {
		return currentUser;
	}



	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}



	public UserBo getUserBo() {
		return userBo;
	}



	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}



	public List<Visite> getAllTerminatedVisits() {
		return allTerminatedVisits;
	}



	public void setAllTerminatedVisits(List<Visite> allTerminatedVisits) {
		this.allTerminatedVisits = allTerminatedVisits;
	}


	@PostConstruct
	public void init(){
		
		User userEnCours=userBo.getByLogin(currentUser.getLogin());
		allTerminatedVisits=visiteBo.findTerminatedVisitsByHote(userEnCours.getFonction());	
		
	}


}
