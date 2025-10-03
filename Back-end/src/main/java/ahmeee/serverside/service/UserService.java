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
	
	final int HOUR = 60 * 60 * 1000;
	final int YEAR = 365 * 24 * 60 * 60 * 1000;

	@Autowired
	private UserRepo repo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public String register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRefreshToken(jwtService.generateToken(user.getUsername(), 10 * YEAR));
		repo.save(user);
		return user.getRefreshToken();
	}

	public String verifyToken(String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			return ("Bad request");
		String token = authHeader.substring(7);

		System.out.println("ninna \n\n\n" + jwtService.extractUsername(token));
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtService.extractUsername(token));
		if (jwtService.validateToken(token, userDetails)) {
			return ("Successful login by Refresh Token");
		}
		return ("Error");
	}

	public String verify(Users user) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (auth.isAuthenticated())
			return jwtService.generateToken(user.getUsername(), 1 * HOUR);
		return "Login failed";
	}
}
