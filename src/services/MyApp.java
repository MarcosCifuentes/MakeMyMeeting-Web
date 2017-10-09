package services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;

public class MyApp {

	public static void restoreDB(EntityManager em) {
		em.getTransaction( ).begin( );
		em.createQuery("DELETE FROM Meeting").executeUpdate();
		em.createQuery("DELETE FROM Site").executeUpdate();
		em.createQuery("DELETE FROM Calendar").executeUpdate();
		em.createQuery("DELETE FROM User").executeUpdate();
		em.getTransaction().commit();
	}

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("makemymeeting");
		EntityManager em = emf.createEntityManager();

		//		g)
		MyApp.restoreDB(em);

		DAOSite.createSite("Sala nro 1", "san martin 154", em);
		DAOSite.createSite("Sala nro 2", "pinto 959", em);

		//		b)
		DAOUser.createUser(em, "Marcelo", "Rodriguez","marcelo@gmail.com" );
		DAOUser.createUser(em,"Mateo", "Darin", "mateo@gmail.com");
		DAOUser.createUser(em,"Lionel", "Messi", "lionel@gmail.com");
		DAOUser.createUser(em, "Hector", "Sanchez","hector@gmail.com" );
		DAOUser.createUser(em, "Marta", "Lopez","marta@gmail.com" );
		DAOUser.createUser(em, "Natalia", "Gomez","natalia@gmail.com" );
		DAOUser.createUser(em, "Oscar", "Nepes","oscar@gmail.com" );
		DAOUser.createUser(em, "Susana", "Gimenez","susana@gmail.com" );
		DAOUser.createUser(em, "Julio", "Iglesias","julio@gmail.com" );
		DAOUser.createUser(em, "Martin", "Casas","martin@gmail.com" );
		
		//		b)
		DAOCalendar.createCalendar("Escolar", 3, em);
		DAOCalendar.createCalendar("Municipal", 5, em);
		DAOCalendar.createCalendar("Juegos", 3, em);
		DAOCalendar.createCalendar("Trabajo", 7, em);
		DAOCalendar.createCalendar("Entrevistas", 3, em);
		DAOCalendar.createCalendar("Viajes", 9, em);
		DAOCalendar.createCalendar("Museos", 11, em);

		//		d)
		Date m1Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 12, 00).getTime();
		Date m1End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 14, 00).getTime();		
		DAOMeeting.createMeeting("Peña",m1Start,m1End, 1, 4, 3, em);


		Date m2Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 15, 00).getTime();
		Date m2End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 18, 00).getTime();	
		DAOMeeting.createMeeting("Morfi",m2Start,m2End, 2,6,5, em);


		Date m3Start = new GregorianCalendar(2017, Calendar.JANUARY, 19, 12, 00).getTime();
		Date m3End = new GregorianCalendar(2017, Calendar.JANUARY, 19, 14, 00).getTime();	
		DAOMeeting.createMeeting("Maraton",m3Start,m3End, 1,24,5, em);


		Date m4Start = new GregorianCalendar(2017, Calendar.FEBRUARY, 19, 12, 00).getTime();
		Date m4End = new GregorianCalendar(2017, Calendar.FEBRUARY, 19, 14, 00).getTime();	
		DAOMeeting.createMeeting("Futbol",m4Start,m4End, 2,24,5, em);


		Date m5Start = new GregorianCalendar(2017, Calendar.MARCH, 19, 12, 00).getTime();
		Date m5End = new GregorianCalendar(2017, Calendar.MARCH, 19, 14, 00).getTime();	
		DAOMeeting.createMeeting("Asalto",m5Start,m5End, 1,8,7, em);


		Date m6Start = new GregorianCalendar(2017, Calendar.MAY, 19, 12, 00).getTime();
		Date m6End = new GregorianCalendar(2017, Calendar.MAY, 19, 14, 00).getTime();	
		DAOMeeting.createMeeting("Inversiones",m6Start,m6End, 2,28,9, em);


		Date m7Start = new GregorianCalendar(2017, Calendar.JUNE, 19, 12, 00).getTime();
		Date m7End = new GregorianCalendar(2017, Calendar.JUNE, 19, 14, 00).getTime();	
		DAOMeeting.createMeeting("Pasantias",m7Start,m7End, 1,29,11, em);


		Date m8Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 18, 12, 00).getTime();
		Date m8End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 18, 14, 00).getTime();	
		DAOMeeting.createMeeting("Cumpleaño",m8Start,m8End, 2,14,13, em);


		Date m9Start = new GregorianCalendar(2017, Calendar.SEPTEMBER, 20, 23, 00).getTime();
		Date m9End = new GregorianCalendar(2017, Calendar.SEPTEMBER, 21, 7, 00).getTime();	
		DAOMeeting.createMeeting("Aniversario",m9Start,m9End, 1,16,15, em);


		Date m10Start = new GregorianCalendar(2017, Calendar.DECEMBER, 24, 9, 00).getTime();
		Date m10End = new GregorianCalendar(2017, Calendar.DECEMBER, 25, 0, 00).getTime();	
		DAOMeeting.createMeeting("Navidad",m10Start,m10End, 2,6,21, em);

		//		c)I)
		System.out.println("Datos del Usuario");
		DAOUser.getUserData(3, em);

		//		c)II)
		System.out.println("Reuniones del usuario en el dia");
		DAOUser.getMeetingsByUserAndDay(3, m1Start, em);

		//		c)III)
		System.out.println("Reuniones del Usuario entre fechas");
		Date dateTest1 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 00, 00).getTime();
		Date dateTest2 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 20, 00, 00).getTime();
		DAOUser.getMeetingsByUserBetweenDates(3, dateTest1, dateTest2, em);

		//		c)IV)
		System.out.println("Reuniones superpuestas con la nueva reunion");
		Date dateOverlapS = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 00, 00).getTime();
		Date dateOverlapE = new GregorianCalendar(2017, Calendar.SEPTEMBER, 20, 00, 00).getTime();
		DAOMeeting.createMeeting("Overlap",dateOverlapS,dateOverlapE, 1, 4, 3, em);
		DAOMeeting.getOverlapMeetings(3, 40, em);

		//		e)
		System.out.println("Datos de las Reuniones");
				DAOMeeting.getMeetingsData(em);
				
		//		f) Realizado en el ejercicio c)III)

		em.close();
		emf.close();
	}
}
