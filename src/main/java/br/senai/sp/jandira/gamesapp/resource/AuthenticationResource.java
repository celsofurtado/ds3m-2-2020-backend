package br.senai.sp.jandira.gamesapp.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.config.security.TokenService;
import br.senai.sp.jandira.gamesapp.model.Login;
import br.senai.sp.jandira.gamesapp.model.User;
import br.senai.sp.jandira.gamesapp.repository.UserRepository;
import br.senai.sp.jandira.gamesapp.resource.dto.TokenDto;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid User user) {

		Optional<User> loginUser = userRepository.findByEmail(user.getEmail());

		user.setName(loginUser.get().getName());
		user.setId(loginUser.get().getId());

		UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(user.getEmail(),
				user.getPassword());

		Authentication authentication = authManager.authenticate(loginData);

		String token = tokenService.gerarToken(authentication);

		return ResponseEntity.ok(new TokenDto(token, 
				"Bearer", 
				user.getName(), 
				user.getEmail(),
				user.getId()));

	}

}
