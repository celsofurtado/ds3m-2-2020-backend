package br.senai.sp.jandira.gamesapp.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.model.User;
import br.senai.sp.jandira.gamesapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> autenticar(@RequestBody User user) {
		
		Optional<User> userOptional = userRepository.getUserByEmailPassword(user.getEmail(), user.getPassword());
		
		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User userSalvo = userRepository.save(user);
		return ResponseEntity.ok(userSalvo);
	}

}
