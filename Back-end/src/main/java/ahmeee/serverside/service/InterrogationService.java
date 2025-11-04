package ahmeee.serverside.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v2.SpeechClient;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import ahmeee.serverside.model.SessionState;
import ahmeee.serverside.model.request.prompts.FirstQuestionPrompt;
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

		//file già creato pronto per venir scritto

		return null;
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

	public void handleChunking(SessionState state) throws IOException {
		if (state.getChunksNumber()%60 == 0) {
			try (SpeechClient speechClient = SpeechClient.create()) {
				String uri = state.getFileObj().getAbsolutePath();
				RecognitionConfig config = RecognitionConfig.newBuilder()
					.setEncoding(AudioEncoding.LINEAR16)
					.setSampleRateHertz(16000)
					.build();
				RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(uri).build();

				//mandalo a google per ottenere il testo trascritto (parziale) 
				//controlla come inviare la richiesta una volta creato il builder e inserito l'uri del file in cui hai salvato
				//aggiungi il risultato ottenuto da google alla string che verrà poi inviata a Gemini, poi svuota il file del suo contenuto
			}
		}
	}

}
