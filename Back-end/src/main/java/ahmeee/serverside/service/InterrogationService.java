package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahmeee.serverside.repository.UserRepo;

@Service
public class InterrogationService {
	
	@Autowired
	UserRepo userRepo;

	//Interrogation request body:
	//	Argument,
	//	Synthesis, 
	//	Ignored piece from last call,
	//	Marks sum,
	//	request quantity

}
