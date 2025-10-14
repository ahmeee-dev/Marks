package ahmeee.serverside.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ahmeee.serverside.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;

@RestController("/requests")
public class RequestController {
	
	@Autowired
	private RequestService service;

	public String interrogation(@RequestHeader Map<String, String> header, @RequestBody String body, HttpServletRequest request) {
		if (!service.isValid(header, body, request))
			return "Invalid request";
		return "interrogation";
	}
}
