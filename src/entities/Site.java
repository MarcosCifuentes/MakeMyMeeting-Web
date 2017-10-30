package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Site {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String address;

	@OneToMany(mappedBy="site",cascade= {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Meeting>meetings;

	public Site() {

	}

	public Site(String name, String address) {
		this.name = name;
		this.address = address;
		this.meetings= new ArrayList<Meeting>();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean addMeeting(Meeting meeting) {
		return this.meetings.add(meeting);
	}
}
