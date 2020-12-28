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
import dz.cerist.mesrs.exception.UserNotFoundException;

import dz.cerist.mesrs.service.UserBo;




/**
 * View bean to add a new user.
 */
@ManagedBean(name = "resetPasswordView")
@ViewScoped
public class ResetPasswordView implements Serializable{

    

	/**
	 * 
	 */
	private static final long serialVersionUID = 5633246505899727623L;

	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;

    private boolean passwordResetSuccessful = false;

    private User user;

    
    
    
    public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	/**
     * Reads the action token and processes it.
     */
    public void processActionToken() {
        if (!passwordResetSuccessful) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
            String actionToken = request.getParameter("uid");

            try {
            	System.out.println("passwordResetSuccessful is :" + passwordResetSuccessful);
            	user = userBo.getUserByActionToken(actionToken);
                System.out.println("USER IS :" + user);
            } catch (UserNotFoundException e) {
            	FacesContext context = FacesContext.getCurrentInstance();
    	        context.addMessage(null, new FacesMessage("Action token not valid"));
                user = null;
            }
        }
    }

    /**
     * Resets the password of the user identified by userIdentifier.
     */
    public void resetPassword() {
        if (user != null) {
            try {
            	userBo.resetPassword(user);
                passwordResetSuccessful = true;
                
            } catch (UserNotFoundException e) {
            	FacesContext context = FacesContext.getCurrentInstance();
    	        context.addMessage(null, new FacesMessage("Could not find user"));
               passwordResetSuccessful = false;
            }
        } else {
            passwordResetSuccessful = false;
        }
    }

    // ---------- getter/setter ----------

    public boolean isPasswordResetSuccessful() {
        return passwordResetSuccessful;
    }

    public void setPasswordResetSuccessful(boolean passwordResetSuccessful) {
        this.passwordResetSuccessful = passwordResetSuccessful;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	


	

}
