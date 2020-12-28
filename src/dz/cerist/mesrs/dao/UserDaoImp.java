package dz.cerist.mesrs.dao;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import dz.cerist.mesrs.entite.User;
import dz.cerist.mesrs.exception.UserNotFoundException;



@Repository("userDao")
@Transactional
public class UserDaoImp  implements Serializable, UserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8889243330035128737L;

	@PersistenceContext(unitName="DbUnitPU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public void persist(User user) {
		// TODO Auto-generated method stub
		em.persist(user);
	}

	@Override
	public User merge(User user) {
		// TODO Auto-generated method stub
		return em.merge(user);
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
		em.remove(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("User.findAll").getResultList();
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);
	}

	@Override
	public User getByLogin(String login) {
		// TODO Auto-generated method stub
		return  (User) em.createNamedQuery("User.getByLogin").setParameter("login", login).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getActiveUsersByRole() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("User.getActiveUsersByRole").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getdesabledUsersByRole() {
		// TODO Auto-generated method stub
		return  em.createNamedQuery("User.getdesabledUsersByRole").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getConnectedUsersByRole() {
		// TODO Auto-generated method stub
		return  em.createNamedQuery("User.getConnectedUsersByRole").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByLogin(String pattern) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("User.findUsersByLogin").setParameter("pattern", '%'+pattern+'%').getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByActionToken(String actionToken)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return  (List<User>) em.createNamedQuery("User.getUserByActionToken").setParameter("actionToken", actionToken).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByIdentifier(String userIdentifier)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return  (List<User>) em.createNamedQuery("User.getUserByIdentifier").setParameter("userIdentifier", userIdentifier).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getActiveUsers() {
		// TODO Auto-generated method stub
		return (List<User>) em.createNamedQuery("User.getActiveUsers").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getdisabledUsers() {
		// TODO Auto-generated method stub
		return (List<User>) em.createNamedQuery("User.getdisabledUsers").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getConnectedUsers() {
		// TODO Auto-generated method stub
		return (List<User>) em.createNamedQuery("User.getConnectedUsers").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getNewUsers() {
		// TODO Auto-generated method stub
		return (List<User>) em.createNamedQuery("User.getNewUsers").getResultList();
	}

	@Override
	public void removeById(Integer id) {
		// TODO Auto-generated method stub
		User user = getById(id);
		em.remove(user);
	}

	@Override
	public Long numberOfUserByUsername(String desiredUsername) {
		// TODO Auto-generated method stub
		
		return (Long) em.createNamedQuery("User.numberOfUserByUsername").setParameter("desiredUsername", desiredUsername).getSingleResult();
		
	}

	@Override
	public Long numberOfUserByEmail(String email) {
		// TODO Auto-generated method stub
		return (Long) em.createNamedQuery("User.numberOfUserByEmail").setParameter("email", email).getSingleResult();
	}

	

}
