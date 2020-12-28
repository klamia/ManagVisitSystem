package dz.cerist.mesrs.web.jsf;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;
//import javax.annotation.Resource;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



import com.mysql.jdbc.StringUtils;


import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.service.MailService;
import dz.cerist.mesrs.service.UserBo;


@ManagedBean
@ViewScoped
public class AdminUsersView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	
	private List<User> users;
	private User selectedUser;
	private String loginId;
	
	private User updatedUser;
	
	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;
	
	
	
	@ManagedProperty(name="mailService", value="#{mailService}")
    private MailService mailService;
	
	
	
	
	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	
	@PostConstruct
    public void init(){
		users=new ArrayList<User>();
		users=userBo.findAll();
		this.updatedUser=new User();
	
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

		
	public void searchUser(String login){
		FacesContext facesContext= FacesContext.getCurrentInstance();
		String message;
		String title;
		if (!StringUtils.isNullOrEmpty(login))users=userBo.findUsersByLogin(login);
		if (users.size()>0){
			message=users.size()+" Utilisateur(s) Trouvé(s)";
			title="Info";
			facesContext.addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		}
		else {
			message="Utilisateur(s) non existant(s)";
			title="Erreur";
			facesContext.addMessage(title, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		}
	}

	
	public void findAllUsers(){
		users=userBo.findAll();
	}
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public User getSelectedUser() {
		return selectedUser;
		
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
		if (this.selectedUser!=null)System.out.println("------Selected user setted to "+selectedUser.getId());
		else System.out.println("------Selected user setted to "+selectedUser);
	}
	
	
	
	
	public User getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}

	public void enableUser(){
		userBo.enableUser(selectedUser);
		//Ajouter la fonction envoie du mail
		String sub="Activation du Compte" ;
		String bo="Bonjour, votre compte est activé, vous pouvez  maintenant se connecter à l'application !";
		mailService.sendEmail(selectedUser.getEmail(), bo, sub);
	
	}
	
	public void desableUser(){
		userBo.desableUser(selectedUser);
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage("Désactivation du Compte Utilisateur réussie "));
	}

	public void deleteUser(int index){
		User user=userBo.getById(this.users.get(index).getId());
		users.remove(index);
		userBo.remove(user);
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage("Suppression du Compte Utilisateur réussie "));
	
	}

  
	public void attribuerRole(User user){
		System.out.println("Select user");
		updatedUser=user;
		
		System.out.print(updatedUser.getLogin());
		
	}
	
	
	public void attributeRole() {
		
	   userBo.merge(updatedUser);
	   FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(null, new FacesMessage("Attribution du role à l'utilisateur réussie "));
	}

	
	
	
	
	
	


}
