package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Calendar;
import entities.User;

public class DAOUser {

	private static DAOUser daoUser;

	private DAOUser(){

	}

	public static DAOUser getInstance() {
		if(daoUser == null)
			daoUser = new DAOUser();
		return daoUser;
	}

	public List<User> getUsers() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT u FROM User u "; 
		Query query = em.createQuery(jpql); 
		List<User> results = query.getResultList(); 
		return results;
	}

	public User getUser(Integer id) {
		EntityManager em=EMF.createEntityManager();
		User user=em.find(User.class, id);
		em.close();
		return user;
	}

	public User createUser(User user) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		user.addCalendar(new Calendar("default", user));
		em.persist(user);
		em.getTransaction().commit();		
		em.close();
		return user;
	}

	public User update(int id, User userUpdate) {
		EntityManager em=EMF.createEntityManager();

		User user = em.find(User.class, id);

		if(user!=null) {
			em.getTransaction().begin();
			user.setName(userUpdate.getName());
			user.setLastname(userUpdate.getLastname());
			user.setEmail(userUpdate.getEmail());
			user.setPassword(userUpdate.getPassword());
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			return user;
		}
		return null;
	}

	public boolean delete(Integer id) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();
		User user = em.find(User.class, id);

		em.close();
		if(user==null)return true;
		return false;
	}

	public User login(String username, String password) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT u FROM User u where u.username = ?1 and u.password =?2"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, username);
		query.setParameter(2, password);
		User user = (User) query.getSingleResult();
		em.close();
		return user;
	}

	public static void restoreDB() {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		em.createQuery("DELETE FROM Meeting").executeUpdate();
		em.createQuery("DELETE FROM Site").executeUpdate();
		em.createQuery("DELETE FROM Calendar").executeUpdate();
		em.createQuery("DELETE FROM User").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

}
