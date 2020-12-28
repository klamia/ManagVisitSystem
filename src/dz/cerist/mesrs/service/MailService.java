package dz.cerist.mesrs.service;



import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.exception.CannotSendMailException;

public interface MailService {

	public boolean sendEmail(String to, String body, String subject);
	public void sendPasswordResetMail(User user, String serverString) throws CannotSendMailException ;
}
