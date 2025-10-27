package ahmeee.serverside.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ahmeee.serverside.model.response.ApiResponse;
import ahmeee.serverside.model.response.InterrogationOutput;
import ahmeee.serverside.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;

@RestController("/requests")
public class RequestController {
	
	@Autowired
	private RequestService service;

	@PostMapping("/start_interrogation")
	public ApiResponse interrogation(@RequestHeader Map<String, String> header, @RequestBody String body, HttpServletRequest request) {

		if (!service.isAuthentic(header, body, request)) {
			ApiResponse<InterrogationOutput> apiResponse = new ApiResponse<>();
			apiResponse.setStatus(401);
			apiResponse.setMessage("invalid request");
			apiResponse.setData(null);
			return apiResponse;
		}
		ApiResponse<InterrogationOutput> apiResponse = service.handleInterrogation(body);		
		return apiResponse;
	}

}
