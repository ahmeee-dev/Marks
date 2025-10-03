package ahmeee.serverside.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.InvalidToken;
import ahmeee.serverside.model.Users;
import ahmeee.serverside.repository.UserRepo;

@Service
public class UserService {
	
	final int HOUR = 60 * 60 * 1000;
	final int YEAR = 365 * 24 * 60 * 60 * 1000;

	@Autowired
	private UserRepo repo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private InvalidTokenService tokenService;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public String register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRefreshToken(jwtService.generateToken(user.getUsername(), 10 * YEAR));
		user.setExpiresAt(Date.from(LocalDateTime.now().plusYears(10).atZone(ZoneId.systemDefault()).toInstant()));
		System.out.print(user.getDeviceId());
		repo.save(user);
		return user.getRefreshToken();
	}

	public String verifyToken(String authHeader, String deviceId) {
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			return ("Bad request");
		String token = authHeader.substring(7);
		Users user = repo.findByUsername(jwtService.extractUsername(token));
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtService.extractUsername(token));
		if (user.getUsername().equals(jwtService.extractUsername(token))
				&& jwtService.validateToken(token, userDetails)
				&& user.getDeviceId().equals(deviceId)
				&& !user.isBlacklisted()
				&& tokenService.isValid(new InvalidToken(user.getRefreshToken(), user.getExpiresAt()))) {
			return ("Successful login by Refresh Token");
		}
		return ("Error");
	}


	//function is called by frontend only if token is missing, generate a new token and give it back to user
	public String verify(Users user) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		Users existingUser = repo.findByUsername(user.getUsername());
		if (auth.isAuthenticated()
				&& !repo.findByUsername(user.getUsername()).isBlacklisted()
				&& tokenService.isValid(new InvalidToken(existingUser.getRefreshToken(), existingUser.getExpiresAt()))) {

			existingUser.setPassword(encoder.encode(user.getPassword()));
			existingUser.setRefreshToken(jwtService.generateToken(user.getUsername(), 10 * YEAR));
			existingUser.setExpiresAt(Date.from(LocalDateTime.now().plusYears(10).atZone(ZoneId.systemDefault()).toInstant()));
			existingUser.setDeviceId(user.getDeviceId());
			repo.save(existingUser);
			return "OK";
		}
		return "Login failed";
	}
}
