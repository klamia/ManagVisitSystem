package dz.cerist.mesrs.service;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.exception.CannotSendMailException;

@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService {

	
	private JavaMailSender mailSender;
	
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

     

	public JavaMailSender getMailSender() {
		return mailSender;
	}



	@Override
	public boolean sendEmail(String to, String body, String subject) {
		try { 
			MimeMessage message = mailSender.createMimeMessage();
			InternetAddress[] address={new InternetAddress(to)};
			message.setRecipients(Message.RecipientType.TO,address);
			message.setSubject(subject);
			message.setSentDate(new java.util.Date());
			message.setContent(body,"text/plain; charset=UTF-8"); 
			mailSender.send(message);
			FacesContext context = FacesContext.getCurrentInstance();
		    context.addMessage(null, new FacesMessage("Activation du Compte Utilisateur Réussie "));
			return true;
			
		} catch (MessagingException e) {
			FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
            String message = msg.getString("emailFailedMsg");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            e.printStackTrace();
            return false;
		}
	}



	@Override
	public void sendPasswordResetMail(User user, String serverString) throws CannotSendMailException {
		
            StringBuilder builder = new StringBuilder();
            builder.append("Dear " + user.getLogin() + ", \n\n");
            builder.append("You have requested help recovering the password for the VisitorManagementSystem user ");
            builder.append(user.getLogin()).append(".\n\n");
            builder.append("Please use the following link to reset your MyProfilWebApp password: \n");
            builder.append("http://" + serverString + "/visitor-management/public/resetPassword.jsf?uid=" + user.getActionToken());
            builder.append("\n\n--\n");
            builder.append("Your Visitor Management Application team");

            try { 
            	MimeMessage message = mailSender.createMimeMessage();
    			InternetAddress[] address={new InternetAddress(user.getEmail())};
    			message.setRecipients(Message.RecipientType.TO,address);
    			message.setSubject("Visitor Management Application password recovery");
    			message.setSentDate(new java.util.Date());
    			message.setText(builder.toString()); 
    			message.saveChanges();
    			mailSender.send(message);
    			FacesContext context = FacesContext.getCurrentInstance();
    		    context.addMessage(null, new FacesMessage("Réenitialisation du mot de passe réussie "));
    			
    			
    		} catch (MessagingException e) {
    			FacesContext context = FacesContext.getCurrentInstance();
                ResourceBundle msg = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
                String message = msg.getString("emailFailedMsg");
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
                e.printStackTrace();
                
    		}
            
       
		
	}
    
		
}


