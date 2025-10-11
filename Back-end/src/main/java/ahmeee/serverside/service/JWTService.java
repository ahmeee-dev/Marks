package ahmeee.serverside.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.UserPrincipal;
import ahmeee.serverside.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {


	@Value("${jwt.secret}")
	private String secretKey;
	
	public String generateToken(Users user, long expirationTime) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("device_id", user.getDeviceId());
		return Jwts.builder()
		.claims()
		.add(claims)
		.subject(user.getUsername())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis() + expirationTime))
		.and()
		.signWith(getKey())
		.compact();

	}

	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String extractDeviceId(String token) {
		return extractClaim(token, claims -> claims.get("device_id", String.class));
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims (String token) {
		return Jwts.parser().verifyWith(getKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	public boolean validateToken(String token, UserPrincipal userPrincipal) {
		final String username = extractUsername(token);

		return (username.equals(userPrincipal.getUsername())
			&& !isTokenExpired(token)
			&& extractDeviceId(token).equals(userPrincipal.getDeviceId()));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public boolean isTokenExpiredAfter(String token, int days) {
		Date expirationDate = extractExpiration(token);
		Date now = new Date();
		long difference = (expirationDate.getTime() - now.getTime())/(24 * 60 * 60* 1000);
		return (difference < days);
	}
}
