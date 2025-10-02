package ahmeee.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ahmeee.serverside.model.UserPrincipal;
import ahmeee.serverside.model.Users;
import ahmeee.serverside.repository.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username){
		Users user = repo.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Couldn't find user " + username);
		return new UserPrincipal(user);
	}

}
