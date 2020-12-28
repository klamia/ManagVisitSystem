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

@FacesValidator("dz.cerist.drx.validator.OrgNameValidatorFr")
public class OrgNameValidatorFr implements Validator{
	
private static final String ORG_PATTERN = "^([A-Z\u00E9\u00E8\u00E7\u00E0\u00F9a-z0-9' \\-]){2,255}$";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String org=(String)value;
		if (org!=null && !org.equals("")){  
		Pattern mask=null;
		mask=Pattern.compile(ORG_PATTERN);
		Matcher matcher=mask.matcher(org);
		if (!matcher.matches()){
            ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
            String message = msg.getString("OrgValidatorMsg");
            String title=  msg.getString("error");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,title+" !: "+ message,null));
	}

	}
	}
}
