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
	private Site site;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private Calendar calendar;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;

	private int personal; //tomamos privado como 1 y publico como 0
	private int remember; //tomamos con recordatorio como 1 y sin como 0
	
	@ManyToMany
	private List<User> invitations;

	public Meeting() {

	}

	public Meeting(String name, Date dateStart, Date dateEnd, Site site, Calendar calendar, User user, int personal, int remember) {
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.site = site;
		this.calendar = calendar;
		this.user = user;
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

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
