package ahmeee.serverside.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invalid_token")
public class InvalidToken {

	@Id
    @JsonProperty("invalid_token")
    @Column(name = "invalid_token")
	String token;
    @JsonProperty("expires_at")
    Date expiresAt;

	public InvalidToken(String token, Date expiresAt) {
		this.token = token;
        this.expiresAt = expiresAt;
	}

    public InvalidToken() {}

    public void setInvalidToken(String token) {
        this.token = token;
    }

    public void setExpiresAt(Date date) {
        this.expiresAt = date;
    }

    public Date getExpiresAt() {
        return this.expiresAt;
    }

    public String getToken() {
        return token;
    }
}
