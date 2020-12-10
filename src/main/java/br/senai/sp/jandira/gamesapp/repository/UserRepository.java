package br.senai.sp.jandira.gamesapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.jandira.gamesapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
	Optional<User> getUserByEmailPassword(String email, String password);
	
	Optional<User> findByEmail(String email);

}
