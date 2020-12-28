package dz.cerist.mesrs.dao;

import java.util.List;



import dz.cerist.mesrs.entite.User;

import dz.cerist.mesrs.exception.UserNotFoundException;




public interface UserDao {
  
	public void persist(User user);

	public User merge(User user);

	public void remove(User user);
	
	public void removeById(Integer id);
	
	public List<User> findAll();

	public User getById(Integer id);
	
	public User getByLogin(String login);
	
	public Long numberOfUserByUsername(String desiredUsername);
	
	public Long numberOfUserByEmail(String email);
	
	public List<Object> getActiveUsersByRole();
	
	public List<Object> getdesabledUsersByRole();
	
	public List<Object> getConnectedUsersByRole();
	
	public List<User> findUsersByLogin(String pattern);

	public List<User> getUsersByActionToken(String actionToken) throws UserNotFoundException ;
	
	public List<User> getUsersByIdentifier(String userIdentifier) throws UserNotFoundException;

    public List<User> getActiveUsers();
	
	public List<User> getdisabledUsers();
	
	public List<User> getConnectedUsers();
	
	public List<User> getNewUsers();


}
