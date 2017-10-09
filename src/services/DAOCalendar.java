package services;

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

	public void createCalendar(String name, int idUser, EntityManager em) {
		em.getTransaction( ).begin( );
		User user = DAOUser.getInstance().getUser(idUser, em);
		Calendar newCalendar = new Calendar (name, user);
		em.persist(newCalendar);
		em.getTransaction().commit();
	}

	public Calendar getCalendar(int idCalendar, EntityManager em) {
		String jpql = "SELECT c FROM Calendar c WHERE c.id = ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idCalendar);
		return (Calendar) query.getSingleResult();
	}
}
