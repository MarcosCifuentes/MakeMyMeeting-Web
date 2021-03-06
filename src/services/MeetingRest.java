package services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeetingRest {

	private String name;
	private String dateStart;
	private String dateEnd;

	private int idSite;

	private int idCalendar;

	private int idUser;

	private int personal; 
	private int remember;

	@JsonCreator
	public MeetingRest(@JsonProperty("name") String name,@JsonProperty("dateStart") String dateStart,@JsonProperty("dateEnd") String dateEnd,@JsonProperty("idSite") int idSite,
			@JsonProperty("idCalendar") int idCalendar,@JsonProperty("idUser") int idUser,@JsonProperty("personal") int personal,@JsonProperty("remember") int remember) {
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

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
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
