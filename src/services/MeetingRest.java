package services;

import java.util.Date;

public class MeetingRest {

	private String name;
	private Date dateStart;
	private Date dateEnd;
	
	private int idSite;

	private int idCalendar;

	private int idUser;

	private int personal; 
	private int remember;
	
	public MeetingRest( String name, Date dateStart, Date dateEnd, int idSite, int idCalendar, int idUser,
			int personal, int remember) {
		super();
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.idSite = idSite;
		this.idCalendar = idCalendar;
		this.idUser = idUser;
		this.personal = personal;
		this.remember = remember;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getIdSite() {
		return idSite;
	}

	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}

	public int getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(int idCalendar) {
		this.idCalendar = idCalendar;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getPersonal() {
		return personal;
	}

	public void setPersonal(int personal) {
		this.personal = personal;
	}

	public int getRemember() {
		return remember;
	}

	public void setRemember(int remember) {
		this.remember = remember;
	} 
	
	
}
