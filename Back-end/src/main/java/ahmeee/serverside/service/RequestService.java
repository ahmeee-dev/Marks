package ahmeee.serverside.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.UserPrincipal;
import ahmeee.serverside.utils.SignatureUtils;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestService {
	
	@Autowired
	private MyUserDetailsService myUserDetials;

	public boolean isValid(Map<String, String> header, String body, HttpServletRequest request) {
		String signature = header.get("X-Signature");
		String method = request.getMethod();
		String path = request.getRequestURI();
		String deviceId = header.get("X-DeviceId");
		String username = header.get("X-Username");
		Long timestamp = Long.valueOf(header.get("X-Timestamp"));
		UserPrincipal userPrincipal = (UserPrincipal)myUserDetials.loadUserByUsername(username);

		if (userPrincipal == null) { return false; }
		if (!isTimeValid(timestamp)) { return false; }
		if (signature == null || deviceId == null || username == null) { return false; }
		
		String userSecret = userPrincipal.getSecret();
		String canonicalString = method + "\n" + path + "\n" + username + "\n" + deviceId + "\n" + timestamp + "\n" + body;
		String expectedSignature = SignatureUtils.generateSignature(userSecret, canonicalString);

		return (expectedSignature.equals(signature));
	}


	private boolean isTimeValid(Long requestTimeStamp) {
		if (requestTimeStamp == null)
			return false;
		Long now = System.currentTimeMillis()/1000;
		return (Math.abs((now - requestTimeStamp)) < 60);
	}
}
