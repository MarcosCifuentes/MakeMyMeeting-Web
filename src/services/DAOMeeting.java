package services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Calendar;
import entities.Meeting;
import entities.Site;

public class DAOMeeting {

	private static DAOMeeting daoMeeting;

	private DAOMeeting(){
	}

	public static DAOMeeting getInstance() {
		if(daoMeeting == null)
			daoMeeting = new DAOMeeting();
		return daoMeeting;
	}

	public List<Meeting> getMeetings() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m "; 
		Query query = em.createQuery(jpql); 
		List<Meeting> results = query.getResultList(); 
		return results;
	}

	public Meeting getMeeting(int idMeeting) {
		EntityManager em=EMF.createEntityManager();
		Meeting meeting =em.find(Meeting.class, idMeeting);
		return meeting;
	}

	public Meeting createMeeting(String name, Date dateStart, Date dateEnd, int idSite, int idCalendar, int personal, int remember) {
		EntityManager em=EMF.createEntityManager();

		Site site = em.find(Site.class, idSite);
		Calendar calendar = em.find(Calendar.class, idCalendar);

		if(site!=null && calendar!=null) {
			if (!DAOSite.getInstance().overlap(site.getId(), dateStart, dateEnd)) {     
				if (!DAOCalendar.getInstance().overlap(calendar.getId(), dateStart, dateEnd)) { 
					em.getTransaction( ).begin();
					Meeting meeting = new Meeting(name,dateStart,dateEnd,site,calendar,personal,remember);
					calendar.addMeeting(meeting);
					site.addMeeting(meeting);
					em.persist(meeting);
					em.persist(calendar);
					em.persist(site);
					em.getTransaction().commit();
					return meeting;
				}else {
					return null;
				}
			}else {
				return null;
			}
		}
		return null;
	}

	public Meeting update(int id,Date date1, Date date2, MeetingRest updateMeeting) {
		EntityManager em=EMF.createEntityManager();

		Meeting meeting = em.find(Meeting.class,id);

		if(meeting!=null) {
			em.getTransaction().begin();
			meeting.setName(updateMeeting.getName());
			meeting.setDateEnd(date1);
			meeting.setDateStart(date2);
			meeting.setPersonal(updateMeeting.getPersonal());
			meeting.setRemember(updateMeeting.getRemember());
			em.persist(meeting);
			em.getTransaction().commit();
			em.close();
			return meeting;
		}
		return null;
	}

	public boolean delete(Integer id) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Meeting.class, id));
		em.getTransaction().commit();
		Meeting meeting= em.find(Meeting.class, id);
		em.close();

		if(meeting==null)return true;
		return false;
	}

	public List<Meeting> getMeetingsByUserAndDay(int idUser, Date date) {
		EntityManager em=EMF.createEntityManager();
		
		int year = date.getYear();
		int month = date.getMonth() + 1;			 //Month se inicia en 0.
		int day = date.getDay();  

		String jpql = "SELECT m FROM Meeting m JOIN m.calendar.user user WHERE user.id = ?1 and extract(day from m.dateStart) = ?2"
				+ " and extract(month from m.dateStart) = ?3"
				+ " and extract(year from m.dateStart) = ?4"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, idUser);
		query.setParameter(2, day);
		query.setParameter(3, month);
		query.setParameter(4, year);
		List<Meeting> results = query.getResultList(); 
		return results;
	}

	public List<Meeting> getMeetingsByUserBetweenDates(int idUser, Date date1, Date date2) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m JOIN m.calendar.user user WHERE user.id = ?1 and m.dateStart BETWEEN ?2 AND ?3"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, idUser);
		query.setParameter(2, date1);
		query.setParameter(3, date2);
		List<Meeting> results = query.getResultList(); 
		return results;
	}

	public List<Meeting> getOverlapMeetings(int idUser, Date inicio, Date fin) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m JOIN m.calendar.user user WHERE user.id = ?3"
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
