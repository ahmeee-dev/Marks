package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import ahmeee.serverside.model.request.InterrogationPrompt;
import ahmeee.serverside.model.request.InterrogationRequest;
import ahmeee.serverside.model.response.InterrogationResponse;


//TODO: to completely review under the new format
// I am expecting the request to be full and validated
@Service
public class InterrogationService {

	@Value("${GEMINI_API_KEY}")
	private String apiKey;

	final String MODEL = "gemini-2.5-flash-lite";

	public InterrogationResponse handleInterrogation(String requestString) {

		try {
			Client client = Client.builder().apiKey(apiKey).build();
			ObjectMapper mapper = new ObjectMapper();

			InterrogationRequest requestJson = mapper.readValue(requestString, InterrogationRequest.class);
			InterrogationPrompt interrogationPromptObject = InterrogationPrompt.createInterrogationPrompt(requestJson);

			String prompt = mapper.writeValueAsString(interrogationPromptObject);
			System.out.println("\n\n\n" + apiKey + "\n\n\n");
			GenerateContentResponse response = client.models.generateContent(MODEL, prompt,null);
			String outputString = response.text();
			outputString = outputString
			.replaceAll("(?s)```json", "")
			.replaceAll("(?s)```", "")
			.trim();
			System.out.println(outputString);
			InterrogationResponse outputJson = mapper.readValue(outputString, InterrogationResponse.class);

			
			return outputJson;
		} catch (JsonProcessingException err) { System.out.print("RRAAAAHHH" + err.getMessage()); return null; }
	}
	//still to manage number grade calculation
	//still to add any record of performances
}
