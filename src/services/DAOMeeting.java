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

	public void createMeeting(String name, Date dateStart, Date dateEnd, int idSite, int idCalendar, int idUser, EntityManager em) {
		em.getTransaction( ).begin( );
		User user = DAOUser.getInstance().getUser(idUser, em);
		Site site = DAOSite.getInstance().getSite(idSite, em);
		Calendar calendar = DAOCalendar.getInstance().getCalendar(idCalendar, em);
//		if (!DAOSite.overlap(idSite, dateStart, dateEnd, em)) {     comentamos esta parte par poder realizar la funcionalidad c)IV)
//			if (!DAOUser.overlap(idUser, dateStart, dateEnd, em)) { ya que con ella evitamos superposiciones de reuniones
				Meeting newMeeting = new Meeting (name, dateStart, dateEnd, site, calendar, user, 0, 0);
				em.persist(newMeeting);
//			}
//			else System.out.println("The meeting "+name+ " overlaps with other meeting of the user " +user.getName()+", cannot create meeting");
//		}
//		else System.out.println("The meeting "+name+ " overlaps with other meeting on the site "+site.getName()+", cannot create meeting");
		em.getTransaction().commit();
	}	

	public void getMeetingsData(EntityManager em) {
		String jpql = "SELECT m FROM Meeting m"; 
		Query query = em.createQuery(jpql); 
		List<Meeting> resultados = query.getResultList();

		for(Meeting  m : resultados) { 
			System.out.println(m.toString()); 
		} 		
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
