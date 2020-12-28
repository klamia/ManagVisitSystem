package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.UserBo;

@ManagedBean
@ViewScoped
public class ProfileView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	private User user;
    
	@ManagedProperty(name="userBo", value="#{userBo}")
    private UserBo userBo;
    
	@ManagedProperty(name="currentUser", value="#{currentUser}")
    private CurrentUser currentUser;
	
 
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
		System.out.println(">>> Calling init method for profile");
		System.out.println("Current user is "+currentUser.getLogin());
		user=userBo.getByLogin(currentUser.getLogin());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
