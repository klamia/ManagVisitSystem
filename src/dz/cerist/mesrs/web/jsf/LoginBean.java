package dz.cerist.mesrs.web.jsf;


import java.io.IOException;
import java.io.Serializable;







import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;




import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;




import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;




import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.UserBo;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4741436392023503874L;
	
	private String username;
	private String password;
 
	
	
	@ManagedProperty(name="currentUser", value="#{currentUser}")
	private CurrentUser currentUser;
	
	
	
	public CurrentUser getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}
	
	
	

	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private User user =new User(); ;
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String login() throws IOException, ServletException {
		
		String message="Utilisateur ou mot de passe erronné";
		String title="Problème de connexion!";
		
		   try {
			   
			   String url = "/j_spring_security_check?j_username=" + username + "&j_password=" + password;
				
				
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();

				
				RequestDispatcher dispatcher = ((ServletRequest) context
						.getRequest())
						.getRequestDispatcher(url);
				
				dispatcher.forward((ServletRequest) context.getRequest(),
						(ServletResponse) context.getResponse());
				
			
				FacesContext.getCurrentInstance().responseComplete();
			
			
	
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				String name;
				if (principal instanceof UserDetails) {
				 name = ((UserDetails)principal).getUsername();
				} else {
				name = principal.toString();
				}	
				System.out.println("///>>> l'utilisateur "+name+" est connecté"); 
			   
				currentUser.setLogin(name);
				
				System.out.println("///>>> Current User "+currentUser.getLogin()+" est connecté");
					
			
		   
			user=userBo.getByLogin(name); 
				
				System.out.println("2-->>> saving user infomation on DB: username from user "+user.getLogin());
				
		//		update user information on DB
				
				user.setConnecte(true);
				
				userBo.merge(user);
			    
			
		   } catch (AuthenticationException ex) {
				System.out.println("Login Failed");
				FacesContext.getCurrentInstance()
						.addMessage(
								title,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										message, null));
			}
		return null;
		
		
	}
	
	
	
	
	public UserBo getUserBo() {
		return userBo;
	}
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}
	public String logout() throws IOException, ServletException {
        /*
		user=userBo.getByLogin(currentUser.getLogin());
		user.setConnecte(false);
		user.setDateDernConnex(new java.util.Date(new java.util.Date().getTime()));
		
		userBo.merge(user);
		System.out.println("4-->>> l'utilisateur "+user.getLogin()+" vient de se déconnecter");
		*/
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
		user=userBo.getByLogin(currentUser.getLogin());
		user.setConnecte(false);
		user.setDateDernConnex(new java.util.Date(new java.util.Date().getTime()));
		
		userBo.merge(user);
		System.out.println("4-->>> l'utilisateur "+user.getLogin()+" vient de se déconnecter");
		
		
		return "logout";
	}


}
