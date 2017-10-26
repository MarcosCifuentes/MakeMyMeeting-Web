package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Calendar;

public class DAOCalendar {

	private static DAOCalendar daoCalendar;

	private DAOCalendar(){
	}

	public static DAOCalendar getInstance() {
		if(daoCalendar == null)
			daoCalendar = new DAOCalendar();
		return daoCalendar;
	}

	public Calendar createCalendar(String name, int idUser) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		Calendar newCalendar = new Calendar (name, idUser);
		em.persist(newCalendar);
		em.getTransaction().commit();
		em.close();
		return newCalendar;
	}

	public Calendar getCalendar(int idCalendar) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT c FROM Calendar c WHERE c.id = ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idCalendar);
		return (Calendar) query.getSingleResult();
	}
	
	public List<Calendar> getCalendars() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT c FROM Calendar c "; 
		Query query = em.createQuery(jpql); 
		List<Calendar> results = query.getResultList(); 
		return results;
	}
	
	public Calendar update(int id, String name, int idUser) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();		
		String jpql = "UPDATE Calendar SET name=?2, idUser=?3, WHERE Calendar.id = ?1"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, id);
		query.setParameter(2, name);
		query.setParameter(3, idUser);
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
