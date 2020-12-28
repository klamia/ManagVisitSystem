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

@FacesValidator("dz.cerist.drx.validator.NameValidator")
public class NameValidator implements Validator {
private static final String NAME_PATTERN = "^([A-Za-z \\-]){2,255}$";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String name=(String)value;
		if (name!=null && !name.equals("")){  
		Pattern mask=null;
		mask=Pattern.compile(NAME_PATTERN);
		Matcher matcher=mask.matcher(name);
		if (!matcher.matches()){
            ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
            String message = msg.getString("nameValidatorMsg");
            String title=  msg.getString("error");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,title+" !: "+ message,null));
	}
	}
	}
}
