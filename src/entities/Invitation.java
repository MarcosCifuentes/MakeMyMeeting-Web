package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invitation {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne (cascade = CascadeType.PERSIST)
	private Meeting meeting;

	@ManyToOne
	private User user;

	private InvitationState state;

	public Invitation () {

	}

	public Invitation(Meeting meeting, User user) {
		super();
		this.meeting = meeting;
		this.user = user;
		this.state = state.Pending;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public InvitationState getState() {
		return state;
	}

	public void accepted() {
		this.state = state.Accepted;
	}	

	public void rejected() {
		this.state = state.Rejected;
	}

	public boolean equals(Invitation invitation) {
		boolean equal = false;
		if (this.getId() == invitation.getId())
			equal= true;
		return equal;
	}
}
