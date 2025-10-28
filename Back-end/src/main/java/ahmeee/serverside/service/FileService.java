package ahmeee.serverside.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import ahmeee.serverside.model.SessionState;
import jakarta.websocket.Session;


@Service
public class FileService {
	
	public void createFile(SessionState state, Session session) throws IOException {

	try {
		File fileObj = new File("temporary_recs/" + session.getId() + ".pcm");
		if (fileObj.createNewFile())
			state.setFileObj(fileObj);
		else
			throw new IOException("File already exists");
		
		
	} catch (IOException err) { System.err.println("Error: " + err.getMessage()); }

	}

	public void appendChunk(String toAppend, SessionState state) {
		byte[] byteArray = Base64.getDecoder().decode(toAppend);
		try (FileOutputStream outputStream = new FileOutputStream(state.getFileObj(), true)) {
			outputStream.write(byteArray);
			state.addChunksCount();
		} catch (IOException err) { System.err.println("Error: " + err.getMessage());}
	}
}
