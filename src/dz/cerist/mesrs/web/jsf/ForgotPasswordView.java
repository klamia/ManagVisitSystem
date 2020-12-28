/*******************************************************************************
 * Copyright 2006 - 2012 Vienna University of Technology,
 * Department of Software Technology and Interactive Systems, IFS
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.exception.CannotSendMailException;
import dz.cerist.mesrs.exception.UserNotFoundException;
import dz.cerist.mesrs.service.MailService;
import dz.cerist.mesrs.service.UserBo;

/**
 * Viewbean to add a new user.
 */
@ManagedBean
@ViewScoped
public class ForgotPasswordView implements Serializable {

    private static final long serialVersionUID = -6437185754610418284L;

    

    @ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;

    
    @ManagedProperty(name="mailService", value="#{mailService}")
    private MailService mailService;
    
    private String userIdentifier;

    
	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	

	
    
    /**
     * Resets the password of the user identified by userIdentifier.
     */
    public String resetPassword() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
            .getRequest();
        String serverString = request.getServerName() + ":" + request.getServerPort();
         System.out.println("SERVER STRING IS :"+serverString);
        
        try {
            User user = userBo.getUserByIdentifier(userIdentifier);
            System.out.println("USER IS :"+user);
            System.out.println("FIRST ACTIONTOKEN IS :"+user.getActionToken());
            userBo.initiateResetPassword(user);
            System.out.println("SECOND ACTIONTOKEN IS :"+user.getActionToken());
            System.out.println("FUNCTION Email Reset is starting :");
            mailService.sendPasswordResetMail(user, serverString);
            System.out.println("FUNCTION Email Reset is FINISHED :");
            
            return "confirmation" ;
            /* FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("A mail with password recovery information has been sent to the email address provided when you created the account"));
	        */
            
            
        } catch (UserNotFoundException e) {
        	FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("No user with this username or email address found"));
        	return null;
        
        } catch (CannotSendMailException e) {
           
        	FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Error sending the password reset mail"));
            return null;  	
       
        }
		
    }

    // ---------- getter/setter ----------
    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

	
}
