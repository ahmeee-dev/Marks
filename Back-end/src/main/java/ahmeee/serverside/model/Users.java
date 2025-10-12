package ahmeee.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String username;
	private String password;
	private String name;
	@JsonProperty("refresh_token")
	private String refreshToken;
	private boolean blacklisted;
	@JsonProperty("device_id")
	private String deviceId;
	//eyJhbGciOiJIUzM4NCJ9.eyJkZXZpY2VfaWQiOiJpcGhvbmUiLCJzdWIiOiJhaG1lZWUiLCJpYXQiOjE3NjAyNzg5NjgsImV4cCI6MTc2ODA1NDk2OH0.5HNVDVrtPeWWidfV5ie8eikDX6unend5P8Ui8H0mVD7zVamty-OLsGgZEGxyeS-d
	// public void setId(int id) {
	// 	this.id = id;
	// }

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setRefreshToken(String token) {
		this.refreshToken = token;
	} 

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
        this.name = name;
    }

	public void setBlacklisted(boolean value) {
        this.blacklisted = value;
    }

	public Long getId() {
		return this.id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

    public String getName() {
        return name;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

	public String getEmail() {
		return email;
	}

   

}
