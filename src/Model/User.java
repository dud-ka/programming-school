package Model;

import Service.BCrypt;

public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private int userGroupId;

	public User() {}

	public User(int id, String username, String password, String email, int userGroupId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userGroupId = userGroupId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	@Override
	public String toString() {
		return "Model.User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
