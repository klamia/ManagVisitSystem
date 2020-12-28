package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.service.RendezVousBo;
import dz.cerist.mesrs.service.UserBo;

@ManagedBean
@RequestScoped
public class ListRdvView implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3405985030728313744L;

	private List<RendezVous> allRdvTerminated;
	
	@ManagedProperty(name="rendezVousBo", value="#{rendezVousBo}")
	private RendezVousBo rendezVousBo;
	
	@ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	
	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;

	
	public List<RendezVous> getAllRdvTerminated() {
		return allRdvTerminated;
	}

	public void setAllRdvTerminated(List<RendezVous> allRdvTerminated) {
		this.allRdvTerminated = allRdvTerminated;
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

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	@PostConstruct
	public void init(){
		allRdvTerminated=rendezVousBo.findTerminatedRDVByUser(userBo.getByLogin(currentUser.getLogin()));	
		
	}


}
