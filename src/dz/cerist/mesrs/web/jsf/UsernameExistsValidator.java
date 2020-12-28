package dz.cerist.mesrs.web.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



import dz.cerist.mesrs.service.UserBo;

@ManagedBean(name = "usernameExistsValidator")
@RequestScoped
public class UsernameExistsValidator implements Validator {

	@ManagedProperty(name="userBo", value="#{userBo}")
    private UserBo userBo;
	
	
	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	
	
	@Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String desiredUsername = (String) value;

        // Just ignore and let required="true" do its job.
        if (desiredUsername == null || desiredUsername.length() == 0) {
            return;
        }

        Long userUsingUsername = userBo.numberOfUserByUsername(desiredUsername);

        if (userUsingUsername > 0) {
            throw new ValidatorException(new FacesMessage("The username is already taken. Please choose another one."));
        }
    }

    

}
