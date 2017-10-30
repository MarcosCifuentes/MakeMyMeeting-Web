package services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarRest {

	private String name;
	private int idUser;

	@JsonCreator
	public CalendarRest(@JsonProperty("name")String name, @JsonProperty("idUser") int idUser) {
		this.name = name;
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
