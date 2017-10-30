package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Calendar {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy="calendar",cascade= {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Meeting> meetings;

	public Calendar() { 

	}

	public Calendar(String name, User user) {
		this.name = name;
		this.user = user;
		this.meetings = new ArrayList<Meeting>();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean addMeeting(Meeting meeting) {
		return this.meetings.add(meeting);
	}

}
