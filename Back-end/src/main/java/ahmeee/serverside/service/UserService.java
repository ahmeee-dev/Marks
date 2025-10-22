package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.UserPrincipal;
import ahmeee.serverside.model.Users;
import ahmeee.serverside.model.response.LoginResponse;
import ahmeee.serverside.repository.UserRepo;

@Service
public class UserService {

	final long HOUR = 60L * 60 * 1000;
	final long MONTH = 30L * 24 * 60 * 60 * 1000;
	final int MAX_DAYS_TO_EXPIRATION = 20;
	final int TOKEN_VALIDITY = 3;

	@Autowired
	private UserRepo repo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	//TODO: check if username or email is already taken
	public String register(Users user) {
		if (user == null)
			return ("Bad request");
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRefreshToken(jwtService.generateToken(user, TOKEN_VALIDITY * MONTH));
		String newSecret = user.setNewSecret();
		repo.save(user);
		return newSecret;
	}

	//Verifies request device id and user entity device_id - check blacklist
	public String verifyToken(String authHeader, String deviceId) {
		System.out.println("yo wassup");
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			return ("Bad request");
		String token = authHeader.substring(7);
		String username = jwtService.extractUsername(token);

		UserPrincipal userPrincipal = null;
		try {
			userPrincipal = (UserPrincipal) myUserDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException err) { return "User not found"; }
		
		if (jwtService.validateToken(token, userPrincipal)
				&& userPrincipal.getDeviceId().equals(deviceId)
				&& userPrincipal.getToken().equals(token)
				&& !userPrincipal.isBlacklisted()) {
					if (jwtService.isTokenExpiredAfter(token, MAX_DAYS_TO_EXPIRATION) == true) {
						Users user = repo.findByUsername(username);
						user.setRefreshToken(jwtService.generateToken(user, MAX_DAYS_TO_EXPIRATION));
						repo.save(user);
						return ("User updated succesfully");
					}
					System.out.println("this nigga is real");
					return ("Successful login by Refresh Token");
		}
		return ("Send to login");
	}

	public LoginResponse login(Users user) {

		UserPrincipal userPrincipal;
		try {
			userPrincipal = (UserPrincipal) myUserDetailsService.loadUserByUsername(user.getUsername());
		} catch (UsernameNotFoundException err) { return null; }

		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (auth.isAuthenticated() && !userPrincipal.isBlacklisted()) {
			Users existingUser = repo.findByUsername(user.getUsername());
			existingUser.setDeviceId(user.getDeviceId());
			String token = jwtService.generateToken(existingUser, 3 * MONTH);
			existingUser.setRefreshToken(token);
			String newSecret = existingUser.setNewSecret();
			repo.save(existingUser);

			LoginResponse loginResponse = new LoginResponse(existingUser.getRefreshToken(), token, newSecret);
			return loginResponse;
		}
		return null;
	}
}
