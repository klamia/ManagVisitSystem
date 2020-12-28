package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;


import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;



import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.UserBo;



@ManagedBean
@ViewScoped
public class SubscribeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private User user;
    private String password, confirmPassword;
    
    @ManagedProperty(name="userBo", value="#{userBo}")
    private UserBo userBo;
    
    @PostConstruct
	public void init(){
    	user=new User();
    }

    
    
	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String saveUser(){
		
			System.out.println(">>>>>>> Adding new user");
			Md5PasswordEncoder passwordEncoder =  new Md5PasswordEncoder();
			String encodedPassword = passwordEncoder.encodePassword(this.password, null);

			this.user.setPassword(encodedPassword);
			this.user.setDateCreation(new java.util.Date(new java.util.Date().getTime()));
			this.user.setConnecte(false);
			this.user.setEnabled(false);
			this.user.setRole(null);
			userBo.persist(user);
			return "success" ;
			
	}
}
