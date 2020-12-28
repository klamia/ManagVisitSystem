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

@FacesValidator("dz.cerist.drx.validator.TelValidator")
public class TelValidator implements Validator {
	
	private static final String TEL_PATTERN = "^((\\+|00)\\d{1,4} ?|0)\\d{2,4} ?\\d?(\\d\\d ?){3}$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String tel=(String)value;
		if (tel!=null && !tel.equals("")){
		   Pattern mask=null;
		   mask=Pattern.compile(TEL_PATTERN);
		   Matcher matcher=mask.matcher(tel);
		    if (!matcher.matches()){
                ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
                String message = msg.getString("telValidatorMsg");
                String title=  msg.getString("error");
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,title+" ! "+message,null));
		    }
	   }
		
	}
}
