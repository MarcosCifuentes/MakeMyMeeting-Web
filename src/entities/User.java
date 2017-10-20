package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String lastname;
	private String email;

	private List<Invitation> invitations;

	public User() {

	}

	public User(String name, String lastname, String email) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.invitations = new ArrayList<Invitation>();		
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
}
