package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
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

import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.RendezVousBo;
import dz.cerist.mesrs.service.UserBo;

@ManagedBean
@ViewScoped
public class RdvsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RendezVous> allRdv;

	@ManagedProperty(name = "rendezVousBo", value = "#{rendezVousBo}")
	private RendezVousBo rendezVousBo;

	@ManagedProperty(name = "currentUser", value = "#{currentUser}")
	private CurrentUser currentUser;

	@ManagedProperty(name = "userBo", value = "#{userBo}")
	private UserBo userBo;

	private RendezVous updatedRdv;
	private RendezVous rdvVisible;

	private Date rdvTime;

	private User userEnCours;

	private User user;

	public User getUserEnCours() {
		return userEnCours;
	}

	public void setUserEnCours(User userEnCours) {
		this.userEnCours = userEnCours;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public RendezVous getUpdatedRdv() {
		return updatedRdv;
	}

	public void setUpdatedRdv(RendezVous updatedRdv) {
		this.updatedRdv = updatedRdv;
	}

	public RendezVous getRdvVisible() {
		return rdvVisible;
	}

	public void setRdvVisible(RendezVous rdvVisible) {
		this.rdvVisible = rdvVisible;
	}

	public RdvsView() {

	}

	@PostConstruct
	public void init() {

		System.out.println("Début de la fonction init");

		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		final Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (((GrantedAuthority) grantedAuthority).getAuthority().equals(
					"ROLE_RESPONSABLE")) {
				allRdv = rendezVousBo.findByUser(userBo.getByLogin(currentUser
						.getLogin()));
				break;
			} else if ((((GrantedAuthority) grantedAuthority).getAuthority()
					.equals("ROLE_AGENT"))
					|| (((GrantedAuthority) grantedAuthority).getAuthority()
							.equals("ROLE_RECEPTIONISTE"))) {
				allRdv = rendezVousBo.findTodayRDV();
				break;
			} else {
				allRdv = rendezVousBo.findAll();
				break;
			}
		}

		this.updatedRdv=new RendezVous();
		System.out.println("Fin de la fonction init");
	}

	public List<RendezVous> getAllRdv() {

		return allRdv;
	}

	public void setAllRdv(List<RendezVous> allRdv) {
		this.allRdv = allRdv;
	}

	public void updateVisible() {
		RendezVous rdvVisible = getRdvVisible();
		rendezVousBo.RendreVisible(rdvVisible);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(
				"Le Rendez-Vous est bien Visible "));

	}

	public Date getRdvTime() {
		return rdvTime;
	}

	public void setRdvTime(Date rdvTime) {
		this.rdvTime = rdvTime;
	}

	public void editRDV(RendezVous rdv) {
		System.out.println("Select rendez-vous");
		rdvTime = rdv.getHeureRdv();
		updatedRdv = rdv;

	}

	public void updateRdv() {
		this.updatedRdv.setHeureRdv(new Time(rdvTime.getTime()));
		rendezVousBo.merge(updatedRdv);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(
				"Modification du Rendez-Vous réussie "));
	}

	public void deleteRdv(int index) {
		RendezVous rendezVous = rendezVousBo.getById(this.allRdv.get(index)
				.getId());
		allRdv.remove(index);
		rendezVousBo.remove(rendezVous);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(
				"Suppression du Rendez-Vous réussie "));

	}

}
