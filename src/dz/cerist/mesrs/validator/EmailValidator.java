package dz.cerist.mesrs.validator;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("dz.cerist.drx.validator.EmailValidator")
public class EmailValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String email=(String)value;
		if (email!=null && !email.equals("")){
		Pattern mask=null;
		mask=Pattern.compile(EMAIL_PATTERN);
		Matcher matcher=mask.matcher(email);
		if (!matcher.matches()){
            ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
            String message = msg.getString("EmailValidatorMsg");
            String title=  msg.getString("error");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,title+" ! "+ message,null));
	}
	}
	}
}
