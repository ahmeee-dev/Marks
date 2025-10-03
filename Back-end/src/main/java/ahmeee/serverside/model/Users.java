package ahmeee.serverside.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String name;
	private String refreshToken;
	private boolean blacklisted;
	private String deviceId;
	private Date expiresAt;
	

	// public void setId(int id) {
	// 	this.id = id;
	// }

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

	public void setExpiresAt(Date expiration) {
		this.expiresAt = expiration;
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

    public Date getExpiresAt() {
        return expiresAt;
    }

    public String getName() {
        return name;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

   

}
