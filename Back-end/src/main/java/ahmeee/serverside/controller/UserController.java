package ahmeee.serverside.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ahmeee.serverside.model.Users;
import ahmeee.serverside.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return service.verify(user);
	}

	@PostMapping("/token_login")
	public String tokenLogin(@RequestHeader("Authorization") String authHeader, @RequestHeader("device_id") String deviceId) {
		return service.verifyToken(authHeader, deviceId);
	}

	@PostMapping("/register")
	public String register(@RequestBody Users user) {
		return service.register(user);
	}
	 
}
