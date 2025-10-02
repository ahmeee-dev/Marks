package ahmeee.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ahmeee.serverside.model.Users;

public interface  UserRepo extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
}
