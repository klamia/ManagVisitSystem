package dz.cerist.mesrs.service;

import java.io.Serializable;

import java.util.List;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.cerist.mesrs.dao.UserDao;
import dz.cerist.mesrs.entite.User;

import dz.cerist.mesrs.exception.UserNotFoundException;


@Service("userBo")
@Transactional
public class UserBoImp implements UserBo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627886937917164039L;

	@Autowired
	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void persist(User user) {
		
		user.setActionToken(UUID.randomUUID().toString());
		userDao.persist(user);

	}

	@Override
	public User merge(User user) {
		return userDao.merge(user);

	}

	@Override
	public void remove(User user) {
		if (user.getId() == null) {
			throw new IllegalArgumentException(
					"Le champ login ne peut pas être nul");
		}
		userDao.removeById(user.getId());
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User getById(Integer id) {
        if (id == null) {
			throw new IllegalArgumentException(
					"Le champ ID ne peut pas être nul ou vide");
		}

		return userDao.getById(id);
	}
	
	

	@Override
	public User getByLogin(String login){
		
		return userDao.getByLogin(login);
	}

	@Override
	public int getActiveUsersNumber() {
		
		return userDao.getActiveUsers().size();
	}

	@Override
	public int getdisabledUsersNumber() {
		
		return userDao.getdisabledUsers().size();
	}

	@Override
	public List<Object> getActiveUsersByRole() {
		
		return userDao.getActiveUsersByRole();
	}

	@Override
	public List<Object> getdesabledUsersByRole() {
		
		return userDao.getdesabledUsersByRole();
	}

	@Override
	public int getNewUsersNumber() {
		
		return userDao.getNewUsers().size();
	}

	@Override
	public List<User> findUsersByLogin(String pattern) {
		
		return (List<User>) userDao.findUsersByLogin(pattern);
	}

	@Override
	public void enableUser(User user) {
		user.setEnabled(true);
		userDao.merge(user);
		
	}

	@Override
	public void desableUser(User user) {
		user.setEnabled(false);
		userDao.merge(user);
		
	}

	@Override
	public int getConnectedUsersNumber() {
		
		return userDao.getConnectedUsers().size();
	}

	@Override
	public List<Object> getConnectedUsersByRole() {
		
		return userDao.getConnectedUsersByRole();
	}

	@Override
	public void roleAttribute(User user) {
		// TODO Auto-generated method stub
		
	
	}

	@Override
	public User getUserByActionToken(String actionToken)
			throws UserNotFoundException {
		
		
        List<User> matchingUsers = (List<User>) userDao.getUsersByActionToken(actionToken);
        
            if (matchingUsers.size() != 1) {
            	System.out.println("{} users matching given actionToken {}" + matchingUsers.size() + "" + actionToken);
                throw new UserNotFoundException(matchingUsers.size() + " users matching given actionToken " + actionToken);
            }

            return matchingUsers.get(0);	
		
	}

	@Override
	public User getUserByIdentifier(String userIdentifier) throws UserNotFoundException {
		
		
		
        List<User> matchingUsers = (List<User>) userDao.getUsersByIdentifier(userIdentifier);

            if (matchingUsers.size() != 1) {
                
                System.out.println("{} users matching given identifier {}" + matchingUsers.size() + "" + userIdentifier);
                throw new UserNotFoundException(matchingUsers.size() + " users matching given identifier " + userIdentifier);
            }

            return matchingUsers.get(0);
	}

	
	/**
     * Initiates password reset for the user.
     * 
     * @param user
     *            the user
     */
	@Override
	public void initiateResetPassword(User user) {
		// TODO Auto-generated method stub
		user.setActionToken(UUID.randomUUID().toString());
		userDao.persist(userDao.merge(user));
		
	}

	
	/**
     * Sends the user a link to reset the password.
     * 
    */ 
	
	
	
	/**
     * Resets the password of the user identified by the actionToken.
     * 
     * @param user
     *            the user
     * @throws UserNotFoundException
     *             if the user could not be found
     */
	
	@Override
	public void resetPassword(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		// We have to find the user because if we use em.merge(user)
        // user.plainPassword will be deleted (because it is transient).

		User foundUser = userDao.getById(user.getId());  
    	if (foundUser == null) {
            System.out.println("Error resetting password. User not found {}."+ user.getLogin());
            throw new UserNotFoundException("Error resetting password. User not found " + user.getLogin());
        }
        foundUser.setPassword(user.getPassword());
        foundUser.setActionToken("");
        userDao.persist(foundUser);
        System.out.println("Reset password for user "+ user.getLogin());
	
	}

	@Override
	public Long numberOfUserByUsername(String desiredUsername) {
		// TODO Auto-generated method stub
		return userDao.numberOfUserByUsername(desiredUsername);
	}

	@Override
	public Long numberOfUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.numberOfUserByEmail(email);
	}
	
	
	
	
	
}
