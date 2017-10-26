package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Calendar;
import entities.User;

public class DAOCalendar {

	private static DAOCalendar daoCalendar;

	private DAOCalendar(){
	}

	public static DAOCalendar getInstance() {
		if(daoCalendar == null)
			daoCalendar = new DAOCalendar();
		return daoCalendar;
	}

	public Calendar createCalendar(String name, User user) {
		EntityManager em=EMF.createEntityManager();
		Calendar newCalendar = createCalendar(name,user,em);
		em.close();
		return newCalendar;
	}
	
	public Calendar createCalendar(String name, User user,EntityManager em) {
		em.getTransaction( ).begin( );
		Calendar newCalendar = new Calendar (name, user);
		em.persist(newCalendar);
		em.getTransaction().commit();
		return newCalendar;
	}

	public Calendar getCalendar(int idCalendar) {
		EntityManager em=EMF.createEntityManager();
		Calendar calendar = getCalendar(idCalendar,em);
		em.close();
		return calendar;
	}

	public Calendar getCalendar(int idCalendar, EntityManager em) {
		String jpql = "SELECT c FROM Calendar c WHERE c.id = ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idCalendar);
		Calendar calendar = (Calendar) query.getSingleResult();
		return calendar;
	}
	
	public List<Calendar> getCalendars() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT c FROM Calendar c "; 
		Query query = em.createQuery(jpql); 
		List<Calendar> results = query.getResultList(); 
		return results;
	}
	
	public Calendar update(int id, String name) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();		
		String jpql = "UPDATE Calendar SET name=?2 WHERE Calendar.id = ?1"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, id);
		query.setParameter(2, name);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		Calendar calendar = getCalendar(id);

		return calendar;
	}

	public boolean delete(Integer id) {
		boolean deleted = false;

		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		String jpql = "DELETE FROM Calendar c WHERE c.id = ?1"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		Calendar calendar =getCalendar(id);
		if (calendar == null) {
			deleted = true;
		}	
		return deleted;
	}
}
