package services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import entities.Site;
import entities.User;
import entities.Meeting;

public class MyApp {

	public static void restoreDB() {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		em.createQuery("DELETE FROM Meeting").executeUpdate();
		em.createQuery("DELETE FROM Site").executeUpdate();
		em.createQuery("DELETE FROM Calendar").executeUpdate();
		em.createQuery("DELETE FROM User").executeUpdate();
		em.getTransaction().commit();
	}

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("makemymeetingweb");
		EntityManager em = emf.createEntityManager();

		//		g)
//		MyApp.restoreDB();

		Site site1 = DAOSite.getInstance().createSite("Sala nro 1", "san martin 154");
		Site site2 = DAOSite.getInstance().createSite("Sala nro 2", "pinto 959");

		//		b)
		User user1 = DAOUser.getInstance().createUser("MarceloRodriguez", "Marcelo", "Rodriguez","marcelo@gmail.com","1" );
		User user2 = DAOUser.getInstance().createUser("MateoDarin", "Mateo", "Darin", "mateo@gmail.com", "2" );
		User user3 = DAOUser.getInstance().createUser("LionelMessi", "Lionel", "Messi", "lionel@gmail.com", "3" );
		User user4 = DAOUser.getInstance().createUser("HectorSanchez", "Hector", "Sanchez","hector@gmail.com", "4"  );
		User user5 = DAOUser.getInstance().createUser("MartaLopez", "Marta", "Lopez","marta@gmail.com","5"  );
		User user6 = DAOUser.getInstance().createUser("NataliaGomez", "Natalia", "Gomez","natalia@gmail.com","6"  );
		User user7 = DAOUser.getInstance().createUser("OscarNepes", "Oscar", "Nepes", "oscar@gmail.com","7" );
		User user8 = DAOUser.getInstance().createUser("SusanaGimenez", "Susana", "Gimenez","susana@gmail.com","8"  );
		User user9 = DAOUser.getInstance().createUser("JulioIglesias", "Julio", "Iglesias","julio@gmail.com","9"  );
		User user10 = DAOUser.getInstance().createUser("MartinCasas", "Martin", "Casas", "martin@gmail.com","10"  );

		//		b)
		entities.Calendar calendar1 = DAOCalendar.getInstance().createCalendar("Escolar", user1);
		entities.Calendar calendar2 = DAOCalendar.getInstance().createCalendar("Municipal", user3);
		entities.Calendar calendar3 = DAOCalendar.getInstance().createCalendar("Juegos", user1);
		entities.Calendar calendar4 = DAOCalendar.getInstance().createCalendar("Trabajo", user5);
		entities.Calendar calendar5 = DAOCalendar.getInstance().createCalendar("Entrevistas", user1);
		entities.Calendar calendar6 = DAOCalendar.getInstance().createCalendar("Viajes", user7);
		entities.Calendar calendar7 = DAOCalendar.getInstance().createCalendar("Museos", user9);

		//		d)
		Date m1Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 12, 00).getTime();
		Date m1End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 14, 00).getTime();		
		Meeting meeting1 = DAOMeeting.getInstance().createMeeting("Peña",m1Start,m1End, site1, calendar1, user1);


		Date m2Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 15, 00).getTime();
		Date m2End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 18, 00).getTime();	
		Meeting meeting2 = DAOMeeting.getInstance().createMeeting("Morfi",m2Start,m2End, site2,calendar2,user3);


		Date m3Start = new GregorianCalendar(2017, Calendar.JANUARY, 19, 12, 00).getTime();
		Date m3End = new GregorianCalendar(2017, Calendar.JANUARY, 19, 14, 00).getTime();	
		Meeting meeting3 = DAOMeeting.getInstance().createMeeting("Maraton",m3Start,m3End, site1,calendar2,user3);


		Date m4Start = new GregorianCalendar(2017, Calendar.FEBRUARY, 19, 12, 00).getTime();
		Date m4End = new GregorianCalendar(2017, Calendar.FEBRUARY, 19, 14, 00).getTime();	
		Meeting meeting4 = DAOMeeting.getInstance().createMeeting("Futbol",m4Start,m4End, site2,calendar2,user3);


		Date m5Start = new GregorianCalendar(2017, Calendar.MARCH, 19, 12, 00).getTime();
		Date m5End = new GregorianCalendar(2017, Calendar.MARCH, 19, 14, 00).getTime();	
		Meeting meeting5 = DAOMeeting.getInstance().createMeeting("Asalto",m5Start,m5End, site1,calendar4,user5);


		Date m6Start = new GregorianCalendar(2017, Calendar.MAY, 19, 12, 00).getTime();
		Date m6End = new GregorianCalendar(2017, Calendar.MAY, 19, 14, 00).getTime();	
		Meeting meeting6 = DAOMeeting.getInstance().createMeeting("Inversiones",m6Start,m6End, site2,calendar6,user7);


		Date m7Start = new GregorianCalendar(2017, Calendar.JUNE, 19, 12, 00).getTime();
		Date m7End = new GregorianCalendar(2017, Calendar.JUNE, 19, 14, 00).getTime();	
		Meeting meeting7 = DAOMeeting.getInstance().createMeeting("Pasantias",m7Start,m7End, site1,calendar7,user9);


		Date m8Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 18, 12, 00).getTime();
		Date m8End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 18, 14, 00).getTime();	
		Meeting meeting8 = DAOMeeting.getInstance().createMeeting("Cumpleaño",m8Start,m8End, site2,calendar1,user1);


		Date m9Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 20, 23, 00).getTime();
		Date m9End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 21, 7, 00).getTime();	
		Meeting meeting9 = DAOMeeting.getInstance().createMeeting("Aniversario",m9Start,m9End, site1,calendar7,user9);


		Date m10Start = new GregorianCalendar(2017, Calendar.DECEMBER, 24, 9, 00).getTime();
		Date m10End = new GregorianCalendar(2017, Calendar.DECEMBER, 25, 0, 00).getTime();	
		Meeting meeting10 = DAOMeeting.getInstance().createMeeting("Navidad",m10Start,m10End, site2,calendar7,user9);

		//		c)I)
		System.out.println("Datos del Usuario");
		user1.toString();

		//		c)II)
		System.out.println("Reuniones del usuario en el dia");
		List<Meeting> meetingsByUserAndDay = DAOUser.getInstance().getMeetingsByUserAndDay(user1.getId(), m1Start);
		for(Meeting  p : meetingsByUserAndDay) { 
			System.out.println(p.toString());
		}

		//		c)III)
		System.out.println("Reuniones del Usuario entre fechas");
		Date dateTest1 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 00, 00).getTime();
		Date dateTest2 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 20, 00, 00).getTime();
		List<Meeting>meetingsByUserBetweenDates = DAOUser.getInstance().getMeetingsByUserBetweenDates(user1.getId(), dateTest1, dateTest2);
		for(Meeting  p : meetingsByUserBetweenDates) { 
			System.out.println(p.toString());
		}
		//		e)
		System.out.println("Datos de las Reuniones");
		List<Meeting>meetings = DAOMeeting.getInstance().getMeetingsData();
		for(Meeting  p : meetings) { 
			System.out.println(p.toString());
		}

		//		f) Realizado en el ejercicio c)III)

//		em.close();
		emf.close();
	}
}
