package ahmeee.serverside.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.UserPrincipal;
import ahmeee.serverside.model.response.ApiResponse;
import ahmeee.serverside.model.response.InterrogationOutput;
import ahmeee.serverside.utils.SignatureUtils;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestService {
	
	@Autowired
	private MyUserDetailsService myUserDetials;

	@Autowired
	private InterrogationService interrogationService;

	public boolean isAuthentic(Map<String, String> header, String body, HttpServletRequest request) {
		String signature = header.get("x-signature");
		String method = request.getMethod();
		String path = request.getRequestURI();
		String deviceId = header.get("x-deviceid");
		String username = header.get("x-username");
		Long timestamp = Long.valueOf(header.get("x-timestamp"));
		UserPrincipal userPrincipal = (UserPrincipal)myUserDetials.loadUserByUsername(username);

		if (userPrincipal == null) { return false; }
		else if (!isTimeValid(timestamp)) { return false; }
		else if (signature == null || deviceId == null || username == null) { return false; }
		
		String userSecret = userPrincipal.getSecret();
		String canonicalString = method + "\n" + path + "\n" + username + "\n" + deviceId + "\n" + timestamp + "\n" + body;
		System.out.println("canonical: " + canonicalString);
		String expectedSignature = SignatureUtils.generateSignature(userSecret, canonicalString);

		System.out.println(path);
		System.out.println("generated signature: " + expectedSignature);
		System.out.println("python signature: " + signature);

		return (expectedSignature.equals(signature));
	}

	private boolean isTimeValid(Long requestTimeStamp) {
		if (requestTimeStamp == null)
			return false;
		Long now = System.currentTimeMillis()/1000;
		return (Math.abs((now - requestTimeStamp)) < 60);
	}

	public ApiResponse<InterrogationOutput> handleInterrogation(String requestBody) {
		InterrogationOutput interrogationResponse = interrogationService.handleInterrogation(requestBody);
		ApiResponse<InterrogationOutput> apiResponse = new ApiResponse<>();
		if (interrogationResponse == null) {
			apiResponse.setData(null);
			apiResponse.setStatus(400);
			apiResponse.setMessage("unable to process request");
			return apiResponse;
		}
		apiResponse.setStatus(200);
		apiResponse.setMessage("successful request");
		apiResponse.setData(interrogationResponse);
		return apiResponse;
	}
}
