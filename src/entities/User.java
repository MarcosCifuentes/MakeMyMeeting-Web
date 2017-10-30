package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;

	private String username;
	private String name;
	private String lastname;
	private String email;
	private String password;

	@OneToMany(mappedBy="user", cascade= {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Calendar>calendars;

	@OneToMany(mappedBy = "user")
	private List<Invitation> invitations;

	public User() {

	}

	public User(String username, String name, String lastname, String email, String password) {
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.invitations = new ArrayList<Invitation>();	
		this.calendars = new ArrayList<Calendar>();		
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

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String apellido) {
		this.lastname = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + "]";
	}

	public void addInvitation(Invitation invitation) {
		invitations.add(invitation);
	}

	public void acceptInvitation(Invitation invitation) {
		for (int i = 0; i < invitations.size(); i++) {
			if (this.invitations.get(i).equals(invitation))
				invitation.accepted();
		}
	}

	public void rejectInvitation(Invitation invitation) {
		for (int i = 0; i < invitations.size(); i++) {
			if (this.invitations.get(i).equals(invitation))
				invitation.rejected();
		}
		invitations.remove(invitation);
	}

	public boolean addCalendar(Calendar calendar) {
		if(this.calendars !=null)return this.calendars.add(calendar);	
		this.calendars = new ArrayList<Calendar>();
		return this.calendars.add(calendar);
	}
}
