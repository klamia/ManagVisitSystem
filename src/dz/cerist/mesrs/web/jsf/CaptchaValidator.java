package dz.cerist.mesrs.web.jsf;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

@ManagedBean(name = "captchaValidator")
@RequestScoped
public class CaptchaValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) {
			String userCaptchaResponse = (String)arg2;
			boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse((HttpServletRequest) (arg0.getExternalContext().getRequest()), userCaptchaResponse);
			if(!captchaPassed){
				 ResourceBundle msg = arg0.getApplication().evaluateExpressionGet(arg0, "#{msg}", ResourceBundle.class);
		            String message = msg.getString("captchaError");
		            String title=  msg.getString("error");
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,title+" ! "+message,null));
			}
	}

}
