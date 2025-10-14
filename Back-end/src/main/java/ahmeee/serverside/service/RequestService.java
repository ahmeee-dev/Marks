package ahmeee.serverside.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.UserPrincipal;
import ahmeee.serverside.repository.UserRepo;
import ahmeee.serverside.utils.Signature;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestService {
	
	@Autowired
	private MyUserDetailsService myUserDetials;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private Signature signature;

	public boolean isValid(Map<String, String> header, String body, HttpServletRequest request) {
		String signature = header.get("X-signature");
		String method = request.getMethod();
		String path = request.getRequestURI();
		String deviceId = header.get("X-header");
		String username = header.get("X-username");
		Long timestamp = Long.valueOf(header.get("X-timestamp"));
		UserPrincipal userPrincipal = (UserPrincipal)myUserDetials.loadUserByUsername(username);

		if (userPrincipal == null) { return false; }
		if (!isTimeValid(timestamp)) { return false; }
		if (signature == null || deviceId == null || username == null) { return false; }
		
		String userSecret = userPrincipal.getSecret();
		String canonicalString = method + "\n" + path + "\n" + username + "\n" + deviceId + "\n" + timestamp + "\n" + body;
		String expectedSignature = Signature.generateSignature(userPrincipal.getSecret(), canonicalString);
		return true;
	}


	private boolean isTimeValid(Long requestTimeStamp) {
		Long now = System.currentTimeMillis()/1000;
		return ((now - requestTimeStamp) < 60);
	}
}
