package ahmeee.serverside.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	private int id;
	private String username;
	private String password;
	private String deviceId;
	private String accessToken;

	public void setId(int id) {
		this.id = id;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setAccessToken(String token) {
		this.accessToken = token;
	} 

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return this.id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}
}
