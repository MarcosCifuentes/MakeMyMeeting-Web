package services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Calendar;
import entities.Meeting;
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

	@SuppressWarnings("unchecked")
	public List<Calendar> getCalendars() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT c FROM Calendar c "; 
		Query query = em.createQuery(jpql); 
		List<Calendar> results = query.getResultList(); 
		return results;
	}

	public Calendar getCalendar(Integer id) {
		EntityManager em=EMF.createEntityManager();
		Calendar calendar=em.find(Calendar.class, id);
		em.close();
		return calendar;
	}

	public Calendar createCalendar(String name, int idUSer) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );	
		User user = em.find(User.class, idUSer);
		Calendar calendar = new Calendar(name,user);
		user.addCalendar(calendar);
		em.persist(user);
		Calendar result = em.find(Calendar.class, calendar.getId());
		em.getTransaction().commit();

		if(result!=null)return result;
		return null;
	}

	public Calendar update(int id, String name) {
		EntityManager em=EMF.createEntityManager();

		Calendar calendar= em.find(Calendar.class, id);

		if(calendar!=null) {
			em.getTransaction().begin();
			calendar.setName(name);
			em.persist(calendar);
			em.getTransaction().commit();
			em.close();
			return calendar;
		}
		return null;
	}

	public boolean delete(Integer id) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Calendar.class, id));
		em.getTransaction().commit();
		Calendar calendar= em.find(Calendar.class, id);
		em.close();

		if(calendar==null)return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean overlap (int idCalendar, Date start, Date end) {
		boolean overlap = true;

		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m WHERE m.calendar.id = ?1"
				+ " AND m.dateStart <= ?2"
				+ " AND ?2 <= m.dateEnd"
				+ " OR m.dateStart <= ?3"
				+ " AND ?2 <= m.dateStart)";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idCalendar);
		query.setParameter(2, start);
		query.setParameter(3, end);
		List<Meeting> results =	query.getResultList();
		if (results.isEmpty()){ 
			overlap=false;
		}			
		return overlap;
	}
}
