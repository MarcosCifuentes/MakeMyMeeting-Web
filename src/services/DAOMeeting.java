package services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Calendar;
import entities.Meeting;
import entities.Site;
import entities.User;

public class DAOMeeting {

	private static DAOMeeting daoMeeting;

	private DAOMeeting(){
	}

	public static DAOMeeting getInstance() {
		if(daoMeeting == null)
			daoMeeting = new DAOMeeting();
		return daoMeeting;
	}

	public Meeting createMeeting(String name, Date dateStart, Date dateEnd, Site site, Calendar calendar, User user) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		if (!DAOSite.getInstance().overlap(site.getId(), dateStart, dateEnd)) {     
			if (!DAOUser.getInstance().overlap(user.getId(), dateStart, dateEnd)) { 
				Meeting newMeeting = new Meeting (name, dateStart, dateEnd, site, calendar, user, 0, 0);
				em.persist(newMeeting);
				em.getTransaction().commit();
				em.close();
				return newMeeting;
			}
			else return null;
		}	
		else return null;
	}	

	public Meeting getMeeting(int idMeeting) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m WHERE m.id = ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idMeeting);
		return (Meeting) query.getSingleResult();
	}
	
	public List<Meeting> getMeetings() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m "; 
		Query query = em.createQuery(jpql); 
		List<Meeting> results = query.getResultList(); 
		return results;
	}

	public Meeting update(int id, String name, Date dateStart, Date dateEnd) {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();		
		String jpql = "UPDATE Meeting SET name=?2, "
				+ "dateStart=?3, dateEnd=?4, WHERE Meeting.id = ?1"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, id);
		query.setParameter(2, name);
		query.setParameter(3, dateStart);
		query.setParameter(4, dateEnd);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		Meeting meeting = getMeeting(id);

		return meeting;
	}

	public boolean delete(Integer id) {
		boolean deleted = false;

		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		String jpql = "DELETE FROM Meeting m WHERE m.id = ?1"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		
		Meeting meeting =getMeeting(id);
		if (meeting == null) {
			deleted = true;
		}	
		return deleted;
	}

	public List<Meeting> getOverlapMeetings(int idUser, Date inicio, Date fin) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m "
				+ "WHERE m.user.id = ?3"
				+ " AND (m.dateStart <= ?1"
				+ " AND ?1 <= m.dateEnd"
				+ " OR m.dateStart <= ?2"
				+ " AND ?1 <= m.dateStart)"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, inicio);
		query.setParameter(2, fin);
		query.setParameter(3, idUser);
		List<Meeting> results = query.getResultList();
		return results;
	}
}
