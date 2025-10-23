package ahmeee.serverside.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ahmeee.serverside.model.Users;
import ahmeee.serverside.model.response.ApiResponse;
import ahmeee.serverside.model.response.LoginResponse;
import ahmeee.serverside.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("/login")
	public ApiResponse<LoginResponse> login(@RequestBody Users user) {
		ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();
		LoginResponse loginResponse = service.login(user);
		if (loginResponse == null){
			apiResponse.setStatus(400);
			apiResponse.setMessage("No bro, didn't go");
			apiResponse.setData(null);
		}
		apiResponse.setStatus(200);
		apiResponse.setMessage("apposto");
		apiResponse.setData(loginResponse);
		return apiResponse;

	}

	@PostMapping("/token_login")
	public String tokenLogin(@RequestHeader("Authorization") String authHeader, @RequestHeader("x-device_id") String deviceId) {
		System.out.println("this nigga came in");
		String answer = service.verifyToken(authHeader, deviceId);
		System.out.println(answer);
		return answer;
	}

	@PostMapping("/register")
	public ApiResponse<LoginResponse> register(@RequestBody Users user) {

		ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();
		LoginResponse registrationResponse = service.register(user);
		if (registrationResponse == null){
			apiResponse.setStatus(400);
			apiResponse.setMessage("No bro, didn't go");
			apiResponse.setData(null);
		}
		apiResponse.setStatus(200);
		apiResponse.setMessage("apposto");
		apiResponse.setData(registrationResponse);
		return apiResponse;

	}
	 
}
