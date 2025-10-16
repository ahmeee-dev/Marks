package ahmeee.serverside.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ahmeee.serverside.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;

@RestController("/requests")
public class RequestController {
	
	@Autowired
	private RequestService service;

	@PostMapping("/start_interrogation")
	public String interrogation(@RequestHeader Map<String, String> header, @RequestBody RequestBody body, HttpServletRequest request) {
		if (!service.isAuthentic(header, body, request))
			return "Fake request";
		//if (service.isValid((CreateInterrogation)body))
		//	return "Invalid request";
		return "request accepted";
	}
}
