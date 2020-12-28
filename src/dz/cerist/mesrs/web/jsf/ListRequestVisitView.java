package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;






import dz.cerist.mesrs.entite.DemandeVisite;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.DemandeVisiteBo;

import dz.cerist.mesrs.service.UserBo;


@ManagedBean
@RequestScoped
public class ListRequestVisitView implements Serializable{
	
	private static final long serialVersionUID = 8867853071528642511L;

    private List<DemandeVisite> allRequestTerminated;
	
    @ManagedProperty(name="demandeVisiteBo", value="#{demandeVisiteBo}")
	private DemandeVisiteBo demandeVisiteBo;
	
    @ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	
    @ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;

	public List<DemandeVisite> getAllRequestTerminated() {
		return allRequestTerminated;
	}

	public void setAllRequestTerminated(List<DemandeVisite> allRequestTerminated) {
		this.allRequestTerminated = allRequestTerminated;
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

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	@PostConstruct
	public void init(){
		
		User userEnCours=userBo.getByLogin(currentUser.getLogin());
		allRequestTerminated=demandeVisiteBo.findTerminatedRequestByHote(userEnCours.getFonction());	
		
	}


}
