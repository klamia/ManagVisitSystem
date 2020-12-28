package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.entite.Visite;
import dz.cerist.mesrs.service.UserBo;
import dz.cerist.mesrs.service.VisiteBo;

@ManagedBean
@ViewScoped
public class VisitsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Visite> allVisits;

	@ManagedProperty(name="visiteBo", value="#{visiteBo}")
	private VisiteBo visiteBo;

	private Visite selectedVisit;

	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;

	@ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	
	
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

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	public VisitsView() {
		
	}
	
	
	@PostConstruct
	public void init() {

		System.out.println("Début de la fonction init");
		
		User userEnCours = userBo.getByLogin(currentUser.getLogin());
		
		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		final Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {

		if (((GrantedAuthority) grantedAuthority).getAuthority().equals("ROLE_RESPONSABLE"))
		{
			allVisits = visiteBo.findVisiteByHote(userEnCours.getFonction());
			break;
		}else
				if ((((GrantedAuthority) grantedAuthority).getAuthority().equals("ROLE_AGENT")) || (((GrantedAuthority) grantedAuthority).getAuthority().equals("ROLE_RECEPTIONISTE")))   
				{
					allVisits=visiteBo.findTodayVisits();
					break;
				}
			else
			{
			allVisits = visiteBo.findAll();
			break;
			}
			
		}

		this.selectedVisit = new Visite();
	}
		
	public List<Visite> getAllVisits() {
		return allVisits;
	}

	public void setAllVisits(List<Visite> allVisits) {
		this.allVisits = allVisits;
	}

	public void selectVisit(Visite visit) {
		this.setSelectedVisit(visit);
		System.out.println("Selected visit is " + this.selectedVisit);
		System.out.println("Visitors function is "
				+ this.selectedVisit.getFonction());

	}

	public Visite getSelectedVisit() {
		return selectedVisit;
	}

	public void setSelectedVisit(Visite selectedVisit) {
		System.out.println("selecting a visitor");
		this.selectedVisit = selectedVisit;
	}

	public void updateVisit() {
		System.out.println("Updating visit in database");
		visiteBo.merge(this.selectedVisit);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String message = "La visite a été modifiée avec succès";
		String title = "Modifier visite:";
		facesContext.addMessage(title, new FacesMessage(
				FacesMessage.SEVERITY_INFO, null, message));
	}

	public void terminerVisit(Visite visit) {
		System.out.println(">>>>>>>>>> Terminer visite");
		visiteBo.endVisit(visit);
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage(" Vous avez bien terminé la Visite "));
	}

	public void terminerVisitManager(Visite visit) {
		System.out.println(">>>>>>>>>> Terminer visite");
		visiteBo.endVisitManager(visit);
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage(" Vous avez bien terminé la Visite "));
		
		
	}

	public void validateVisit(Visite visit) {
		System.out.println(">>>>>> valider visite");
		visiteBo.checkingVisitManager(visit);
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage(" Vous avez bien validé la Visite "));
	}

	public void checkinVisit(Visite visit) {
		System.out.println(">>>>>>>>>> checkin de la visite");
		visiteBo.checkingVisit(visit);
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage(" Vous avez bien marqué l'entrée du visiteur "));
	}
}
