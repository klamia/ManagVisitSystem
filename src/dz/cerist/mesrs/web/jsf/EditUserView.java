package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;







import com.mysql.jdbc.StringUtils;



import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.UserBo;
import dz.cerist.mesrs.web.util.RandomPassword;


@ManagedBean
@ViewScoped
public class EditUserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private User user;
    
    private String tabIndex;
   
    
    @ManagedProperty(name="userBo", value="#{userBo}")
    private UserBo userBo;
	
    public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	public void init(String id){
		System.out.println("calling init method");
		user=userBo.getById(Integer.valueOf(id));
		if (StringUtils.isNullOrEmpty(tabIndex)) this.setTabIndex("0");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void updateUser(String tabIndex){
		System.out.println("updating user");
		FacesContext facesContext= FacesContext.getCurrentInstance();
		String message;
		String title;
		userBo.merge(user);
		this.setTabIndex(tabIndex);
		message=" Utilisateur modifié avec succés";
		title="Info";
		facesContext.addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public String getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public void newPassword(String tabIndex){
		String pwd=RandomPassword.generatePassword();
		Md5PasswordEncoder passwordEncoder =  new Md5PasswordEncoder();
		String encodedPassword = passwordEncoder.encodePassword(pwd, null);
		
		this.user.setPassword(encodedPassword);
		FacesContext facesContext= FacesContext.getCurrentInstance();
		String message;
		String title;
		userBo.merge(user);
		this.setTabIndex(tabIndex);
		//Ici il manque l'envoie du mot de passe par email. 
		message="Mot de passe modifié avec succés";
		title="Info";
		facesContext.addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

}
