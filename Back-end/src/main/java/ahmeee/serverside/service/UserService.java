package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.Users;
import ahmeee.serverside.repository.UserRepo;

@Service
public class UserService {

	final long HOUR = 60L * 60 * 1000;
	final long MONTH = 30L * 24 * 60 * 60 * 1000;

	@Autowired
	private UserRepo repo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	//still to check if username or email is already taken,
	public String register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRefreshToken(jwtService.generateToken(user, 3 * MONTH));
		repo.save(user);
		return user.getRefreshToken();
	}

	//TODO: Verify token device id and device_id - check blacklist - check if < 20 dd from exp and in case gen new tkn
	public String verifyToken(String authHeader, String deviceId) {
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			return ("Bad request");
		String token = authHeader.substring(7);
		String username = jwtService.extractUsername(token);

		Users user = repo.findByUsername(username);
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
		
		if (jwtService.validateToken(token, userDetails)
				&& user.getDeviceId().equals(deviceId)
				&& !user.isBlacklisted()) {
					return ("Successful login by Refresh Token");
		}
		return ("Error");
	}


	//function is called by frontend only if token is missing, generate a new token and give it back to user
	//TODO : verify if it's correct
	public String verify(Users user) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		Users existingUser = repo.findByUsername(user.getUsername());
		if (auth.isAuthenticated()
				&& !repo.findByUsername(user.getUsername()).isBlacklisted()) {

			existingUser.setPassword(encoder.encode(user.getPassword()));
			existingUser.setRefreshToken(jwtService.generateToken(existingUser, 3 * MONTH));
			existingUser.setDeviceId(user.getDeviceId());
			repo.save(existingUser);
			return "OK";
		}
		return "Login failed";
	}
}
