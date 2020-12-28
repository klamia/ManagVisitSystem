package dz.cerist.mesrs.web.jsf;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "passwordValidator")
@RequestScoped
public class PasswordValidator implements Validator{

	private static final String PASSWORD_PATTERN = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%=:\\?]).{8,10})$"; 

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
    String password=(String)value;
	if (password!=null && !password.equals("")){
	boolean error=false;
	ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
    String message=null;
    String title=null;
	Pattern mask=null;
	mask=Pattern.compile(PASSWORD_PATTERN);
	Matcher matcher=mask.matcher(password);
	if (!matcher.matches()){
       message = msg.getString("passwordValidatorMsg");
       title=  msg.getString("error");
       error=true;
   }
	else {
		 UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("compareTo");
		 UIInput uiInputConfirmPassword2 = (UIInput) component.findComponent("confirmPassword");
		 String confirmPassword = uiInputConfirmPassword .getSubmittedValue().toString();
		 if (confirmPassword  == null || confirmPassword.isEmpty() ) {
			 message =msg.getString("confirmPwdMsg");
		     title=  msg.getString("error");
			 error=true;
		  }
		  else
		  if (!password.equals(confirmPassword)) {
			  uiInputConfirmPassword2.setValid(false);
			  message = msg.getString("passwordError"); 
			  title=  msg.getString("error");
			  error=true;
		  }
	    }	
	if (error)throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, title+"! "+message, null));
 }
}
}
