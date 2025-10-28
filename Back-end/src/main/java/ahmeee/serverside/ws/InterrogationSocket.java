package ahmeee.serverside.ws;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ahmeee.serverside.model.SessionState;
import ahmeee.serverside.model.request.prompts.FirstQuestionPrompt;
import ahmeee.serverside.model.response.FirstQuestionResponse;
import ahmeee.serverside.service.InterrogationService;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/* 
 * dal client invio al server una richiesta inizaie, alla quale il server reagisce controllando che la cartella 
 * addetta al salvataggio momentaneo delle registrazioni sia disponibile. Questo OnOpen.
 * All'arrivo del messaggio di init andremo a creare il file, il quale vedrà costanti Append, fino alla closure, o al timeout.
 * 
 */

@ServerEndpoint("/ws")
public class InterrogationSocket {

    @Autowired
    public InterrogationService interrogationService;

    private static Map<Session, SessionState> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session, new SessionState());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode json = mapper.readTree(message);
            String type = json.get("type").asText();
            SessionState state = sessions.get(session);

            switch (type) {
                case "init":
                    FirstQuestionResponse firstQuestionResponse = interrogationService.handleFirstQuestion(json, state);
                    String response = mapper.writeValueAsString(firstQuestionResponse);
                    session.getAsyncRemote().sendText(response);
                    break;

                case "audio_chunk":
                    String chunkBase64 = json.get("chunk").asText();
                    state.addAudioChunk(Base64.getDecoder().decode(chunkBase64));
                    break;

                case "response_done":
                    // processa audio accumulato e invia feedback
                    Feedback feedback = processResponse(state);
                    sendFeedback(session, feedback);
                    break;

                default:
                    System.out.println("Tipo messaggio sconosciuto: " + type);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }


}
