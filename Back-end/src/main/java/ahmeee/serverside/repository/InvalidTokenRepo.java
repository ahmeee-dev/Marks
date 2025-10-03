package ahmeee.serverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ahmeee.serverside.model.InvalidToken;

public interface  InvalidTokenRepo extends JpaRepository<InvalidToken, String> {
}
