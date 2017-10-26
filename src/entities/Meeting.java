package entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Meeting {

	public static final int PRIVATE = 1;
	public static final int PUBLIC = 0;
	public static final int WITH = 1;
	public static final int WITHOUT = 0;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Date dateStart;
	private Date dateEnd;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private int idSite;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private int idCalendar;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private int idUser;

	private int personal; //tomamos privado como 1 y publico como 0
	private int remember; //tomamos con recordatorio como 1 y sin como 0
	
	@ManyToMany
	private List<User> invitations;

	public Meeting() {

	}

	public Meeting(String name, Date dateStart, Date dateEnd, int idSite, int idCalendar, int idUser, int personal, int remember) {
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.idSite = idSite;
		this.idCalendar = idCalendar;
		this.idUser = idUser;
		this.personal = personal;
		this.remember = remember;
		this.invitations = new ArrayList<User>();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSite() {
		return idSite;
	}

	public void setSite(int idSite) {
		this.idSite = idSite;
	}

	public int getCalendar() {
		return idCalendar;
	}

	public void setCalendar(int idCalendar) {
		this.idCalendar = idCalendar;
	}

	public int getUser() {
		return idUser;
	}

	public void setUser(int idUser) {
		this.idUser = idUser;
	}

	public int isPersonal() {
		return personal;
	}

	public void setPersonal(int personal) {
		this.personal = personal;
	}

	public int isRemember() {
		return remember;
	}

	public void setRemember(int remember) {
		this.remember = remember;
	}

	public String toStringName() {
		return "Meeting [name=" + name + "]";
	}
	
	public void addInvitation(User invitation) {
		invitations.add(invitation);
	}
	
	public void removeInvitation(User invitation) {
		invitations.remove(invitation);
	}
}
