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

import dz.cerist.mesrs.entite.DemandeVisite;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.entite.Visite;
import dz.cerist.mesrs.service.DemandeVisiteBo;
import dz.cerist.mesrs.service.UserBo;
import dz.cerist.mesrs.service.VisiteBo;

@ManagedBean
@ViewScoped
public class RequestsVisitView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6612410538622398174L;

	private List<DemandeVisite> allRequests;

	@ManagedProperty(name = "demandeVisiteBo", value = "#{demandeVisiteBo}")
	private DemandeVisiteBo demandeVisiteBo;

	private DemandeVisite selectedRequest;

	@ManagedProperty(name = "userBo", value = "#{userBo}")
	private UserBo userBo;

	@ManagedProperty(name = "currentUser", value = "#{currentUser}")
	private CurrentUser currentUser;

	@ManagedProperty(name = "visiteBo", value = "#{visiteBo}")
	private VisiteBo visiteBo;

	private Visite visiteNotProgramed;

	public DemandeVisiteBo getDemandeVisiteBo() {
		return demandeVisiteBo;
	}

	public void setDemandeVisiteBo(DemandeVisiteBo demandeVisiteBo) {
		this.demandeVisiteBo = demandeVisiteBo;
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

	public VisiteBo getVisiteBo() {
		return visiteBo;
	}

	public void setVisiteBo(VisiteBo visiteBo) {
		this.visiteBo = visiteBo;
	}

	public List<DemandeVisite> getAllRequests() {
		return allRequests;
	}

	public void setAllRequests(List<DemandeVisite> allRequests) {
		this.allRequests = allRequests;
	}

	public DemandeVisite getSelectedRequest() {
		return selectedRequest;
	}

	public void setSelectedRequest(DemandeVisite selectedRequest) {
		this.selectedRequest = selectedRequest;
	}

	public Visite getVisiteNotProgramed() {
		return visiteNotProgramed;
	}

	public void setVisiteNotProgramed(Visite visiteNotProgramed) {
		this.visiteNotProgramed = visiteNotProgramed;
	}

	public RequestsVisitView() {
	}

	@PostConstruct
	public void init() {

		User userEnCours = userBo.getByLogin(currentUser.getLogin());

		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		final Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {

			if (((GrantedAuthority) grantedAuthority).getAuthority().equals(
					"ROLE_RESPONSABLE")) {
				allRequests = demandeVisiteBo.findByHote(userEnCours
						.getFonction());
				break;
			} else if ((((GrantedAuthority) grantedAuthority).getAuthority()
					.equals("ROLE_AGENT"))
					|| (((GrantedAuthority) grantedAuthority).getAuthority()
							.equals("ROLE_RECEPTIONISTE"))) {
				allRequests = demandeVisiteBo.findTodayRequests();
				break;
			} else {
				allRequests = demandeVisiteBo.findAll();
				break;
			}

		}
		this.selectedRequest = new DemandeVisite();
		visiteNotProgramed = new Visite();
	}

	public void selectRequestVisit(DemandeVisite demVisit) {

		this.setSelectedRequest(demVisit);
		System.out.println("Selected request is " + this.selectedRequest);

	}

	public void updateRequestVisit() {

		System.out.println("Updating request visit in database");
		demandeVisiteBo.merge(this.selectedRequest);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(
				"Modification de la Demande Visite réussie "));
	}

	public void creerVisite() {

		User user = userBo.getByLogin(currentUser.getLogin());
		visiteNotProgramed = demandeVisiteBo.creerVisite(this.selectedRequest);
		visiteNotProgramed.setUser(user);
	}

	public void saveNotProgrammedVisit() {

		visiteBo.persist(visiteNotProgramed);
		this.selectedRequest.setVisite(visiteNotProgramed);
		this.selectedRequest.setEtat("En Cours");
		demandeVisiteBo.merge(this.selectedRequest);

	}

	public void acceptRequestVisit() {

		demandeVisiteBo.accepterDemande(this.selectedRequest);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(
				" La Demande Visite est bien acceptée "));

	}

	public void rejectRequestVisit() {

		demandeVisiteBo.rejeterDemande(this.selectedRequest);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(
				" La Demande Visite est bien rejetée "));

	}

}
