package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.InvalidToken;
import ahmeee.serverside.repository.InvalidTokenRepo;

@Service
public class InvalidTokenService {

	@Autowired
	InvalidTokenRepo repo;

	public void setInvalidToken(InvalidToken token) {
		repo.save(token);
	}

	//true == VALID
	//false == INVALID
	public boolean isValid(InvalidToken token) {
		InvalidToken found = repo.findById(token.getToken()).orElse(new InvalidToken("null", null));

		return (found.getToken().equals("null"));

		
	}
}
