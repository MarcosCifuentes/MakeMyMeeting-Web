package login;

import java.io.Serializable;

public class Credential implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

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


}
