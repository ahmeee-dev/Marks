package ahmeee.serverside.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class UserPrincipal implements UserDetails {

	private final Users user;

	public UserPrincipal(Users user) { this.user = user; }

	@Override 
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public String getDeviceId() {
		return user.getDeviceId();
	}

	public String getToken() {
		return user.getRefreshToken();
	}

	public boolean  isBlacklisted() {
		return user.isBlacklisted();
	}

	public String getSecret() {
		return user.getSecret();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}
	
	//TODO: implement
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//TODO: implement
	@Override
	public boolean isEnabled() {
		return true;
	}


}
