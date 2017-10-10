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

	public Meeting createMeeting(String name, Date dateStart, Date dateEnd, Site site, Calendar calendar, User user, EntityManager em) {
		em.getTransaction( ).begin( );
		if (!DAOSite.getInstance().overlap(site.getId(), dateStart, dateEnd, em)) {     
			if (!DAOUser.getInstance().overlap(user.getId(), dateStart, dateEnd, em)) { 
				Meeting newMeeting = new Meeting (name, dateStart, dateEnd, site, calendar, user, 0, 0);
				em.persist(newMeeting);
				em.getTransaction().commit();
				return newMeeting;
			}
			else return null;
		}	
		else return null;
	}	

	public List getMeetingsData(EntityManager em) {
		String jpql = "SELECT m FROM Meeting m"; 
		Query query = em.createQuery(jpql); 
		List<Meeting> resultados = query.getResultList();

		return resultados;		
	}

	public void getOverlapMeetings(int idUser, int idMeeting, EntityManager em) {
		String jpql = "SELECT m FROM Meeting m JOIN Meeting m2 ON (m2.id = ?1)"
				+ "WHERE m.user.id = ?2"
				+ " AND (m.dateStart <= m2.dateStart"
				+ " AND m2.dateStart <= m.dateEnd"
				+ " OR m.dateStart <= m2.dateEnd"
				+ " AND m2.dateStart <= m.dateStart)"
				+ " AND m.id != ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idMeeting);
		query.setParameter(2, idUser);
		List<Meeting> results = query.getResultList(); 
		for(Meeting  m : results) { 
			System.out.println(m.toStringName()); 
		}
	}
}
