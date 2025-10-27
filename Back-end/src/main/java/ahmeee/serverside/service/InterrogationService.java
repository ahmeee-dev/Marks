package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import ahmeee.serverside.model.request.InterrogationInput;
import ahmeee.serverside.model.request.InterrogationPrompt;
import ahmeee.serverside.model.response.InterrogationOutput;


//TODO:  manage number grade calculation
//TODO:  add any record of performances
//TODO:  handle errors properly

// I am expecting the request to be full and validated
@Service
public class InterrogationService {

	@Value("${GEMINI_API_KEY}")
	private String apiKey;

	final String MODEL = "gemini-2.5-flash-lite";

	public InterrogationOutput handleInterrogation(String requestString) {

		try {
			Client client = Client.builder().apiKey(apiKey).build();
			ObjectMapper mapper = new ObjectMapper();

			InterrogationInput interrogationInput = mapper.readValue(requestString, InterrogationInput.class);
			InterrogationPrompt interrogationPrompt = InterrogationPrompt.createInterrogationPrompt(interrogationInput);

			String prompt = mapper.writeValueAsString(interrogationPrompt);
			
			GenerateContentResponse response = client.models.generateContent(MODEL, prompt,null);
			String outputString = response.text();
			outputString = outputString
			.replaceAll("(?s)```json", "")
			.replaceAll("(?s)```", "")
			.trim();
			System.out.println(outputString);
			InterrogationOutput outputJson = mapper.readValue(outputString, InterrogationOutput.class);

			return outputJson;
		} catch (JsonProcessingException err) { System.out.print("Error generating answer" + err.getMessage()); return null; }
	}

}
