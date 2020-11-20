package br.senai.sp.jandira.gamesapp.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.model.User;
import br.senai.sp.jandira.gamesapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{email}/{password}")
	public ResponseEntity<?> autenticar(@PathVariable String email, @PathVariable String password) {
		
		Optional<User> userOptional = userRepository.getUserByEmailPassword(email, password);
		
		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
