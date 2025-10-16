package ahmeee.serverside.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ahmeee.serverside.model.request.CreateInterrogationRequest;
import ahmeee.serverside.model.request.InterrogationPrompt;
import ahmeee.serverside.model.response.InterrogationResponse;

@Service
public class InterrogationService {

	public InterrogationResponse handleInterrogation(CreateInterrogationRequest request) {

		ObjectMapper mapper = new ObjectMapper();
		InterrogationPrompt interrogationPromptObject = InterrogationPrompt.createInterrogationPrompt(request);
		try {
			String prompt = mapper.writeValueAsString(interrogationPromptObject);
			//try invio a Gemini
			//String output = outputGemini
			//output convertito a oggetto InterrogationResponse
			//return oggetto InterrogationResponse
			return null;
		} catch (JsonProcessingException err) { System.out.print(err.getMessage()); return null; }
		}

}
