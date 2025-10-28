package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import ahmeee.serverside.model.SessionState;
import ahmeee.serverside.model.request.InterrogationInput;
import ahmeee.serverside.model.request.prompts.FirstQuestionPrompt;
import ahmeee.serverside.model.request.prompts.InterrogationPrompt;
import ahmeee.serverside.model.response.FirstQuestionResponse;
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
	Client client = Client.builder().apiKey(apiKey).build();

	final private ObjectMapper mapper = new ObjectMapper();


	public InterrogationOutput handleInterrogation(String requestString) {


		try {

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

	public FirstQuestionResponse handleFirstQuestion(JsonNode json, SessionState state) throws JsonProcessingException {

		String argument = json.get("argument").asText();
        int difficulty = json.get("difficulty").asInt(2);
        boolean languageEvaluation = json.get("language_evaluation").asBoolean();
        state.setArgument(argument);
        state.setDifficulty(difficulty);
        state.setLanguageEvaluation(languageEvaluation);

        FirstQuestionPrompt firstQuestionPrompt = FirstQuestionPrompt.createFirstQuestionPrompt(argument, difficulty);
        String prompt = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(firstQuestionPrompt);
	
		GenerateContentResponse responseObject = client.models.generateContent(MODEL, prompt,null);
		String response = responseObject.text();
		FirstQuestionResponse firstQuestionResponse = mapper.readValue(response, FirstQuestionResponse.class);

		return firstQuestionResponse;
	}

}
