package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Meeting;
import entities.Site;
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

	public User createUser(String name, String lastName, String email) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );	
		User newUser = new User(name,lastName,email);
		em.persist(newUser);
		em.getTransaction().commit();
		DAOCalendar.getInstance().createCalendar("default", newUser);
		return newUser;
	}

	public User getUser(int idUser) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT u FROM User u WHERE u.id = ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUser);
		return (User) query.getSingleResult();
	}
	
	public User update(int id,String name, String lastName,String email) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();		
		String jpql = "UPDATE User SET name=?2, "
				+ "lastName=?3, email=?4 WHERE User.id = ?1"; 
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, id);
        query.setParameter(2, name);
        query.setParameter(3, lastName);
        query.setParameter(4, email);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        User user = getUser(id);
 
		return user;
	}
	
	public boolean delete(Integer id) {
		boolean deleted = false;

		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		String jpql = "DELETE FROM User u WHERE u.id = ?1"; 
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        
        User user =getUser(id);
		if (user == null) {
			deleted = true;
		}	
		return deleted;
	}

	public List<Meeting> getMeetingsByUserAndDay(User user, Date date) {
		EntityManager em=EMF.createEntityManager();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;			 //Month se inicia en 0.
		int day = cal.get(Calendar.DAY_OF_MONTH);     	

		String jpql = "SELECT m FROM Meeting m where (m.user.id = ?1) and extract(day from m.dateStart) = ?2"
				+ " and extract(month from m.dateStart) = ?3"
				+ " and extract(year from m.dateStart) = ?4"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, user.getId());
		query.setParameter(2, day);
		query.setParameter(3, month);
		query.setParameter(4, year);
		List<Meeting> results = query.getResultList(); 
		return results;
	}

	public List<Meeting> getMeetingsByUserBetweenDates(User user, Date date1, Date date2) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m where (m.user.id = ?1) and m.dateStart BETWEEN ?2 AND ?3"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, user.getId());
		query.setParameter(2, date1);
		query.setParameter(3, date2);
		List<Meeting> results = query.getResultList(); 
		return results;
	}

	public boolean overlap (int idUSer, Date start, Date end) {
		boolean overlap = true;

		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m WHERE m.user.id = ?1"
				+ " AND m.dateStart <= ?2"
				+ " AND ?2 <= m.dateEnd"
				+ " OR m.dateStart <= ?3"
				+ " AND ?2 <= m.dateStart)";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUSer);
		query.setParameter(2, start);
		query.setParameter(3, end);
		List<Meeting> results =	query.getResultList();
		System.out.println(results);
		if (results.isEmpty()){ 
			overlap=false;
		}			
		return overlap;
	}
}
