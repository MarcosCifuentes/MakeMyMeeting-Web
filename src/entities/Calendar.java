package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Calendar {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private int idUser;

	public Calendar() { 

	}

	public Calendar(String name, int idUser) {
		this.name = name;
		this.idUser = idUser;
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

	public int getUser() {
		return idUser;
	}

	public void setUser(int idUser) {
		this.idUser = idUser;
	}

}
