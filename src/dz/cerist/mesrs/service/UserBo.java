package dz.cerist.mesrs.service;



import java.util.List;





import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.exception.UserNotFoundException;




public interface UserBo {
	
	public void persist(User user);

	public User merge(User user);

	public void remove(User user);
	
	public void enableUser(User user);
	
	public void desableUser(User user);

	public void roleAttribute(User user);
	
	public List<User> findAll();

	public User getById(Integer id);
	
	public User getByLogin(String login);
	
	public int getActiveUsersNumber();
	
	public int getdisabledUsersNumber();
	
	public int getConnectedUsersNumber();
	
	public Long numberOfUserByUsername(String desiredUsername);
    
	public Long numberOfUserByEmail(String email);
	
	public List<Object> getActiveUsersByRole();
	
	public List<Object> getdesabledUsersByRole();
	
	public List<Object> getConnectedUsersByRole();
	
	public int getNewUsersNumber();
	
	public List<User> findUsersByLogin(String pattern);

	public User getUserByActionToken(String actionToken) throws UserNotFoundException ;
	
	public User getUserByIdentifier(String userIdentifier) throws UserNotFoundException;

	public void initiateResetPassword(User user);
    
	public void resetPassword(User user) throws UserNotFoundException;


}
