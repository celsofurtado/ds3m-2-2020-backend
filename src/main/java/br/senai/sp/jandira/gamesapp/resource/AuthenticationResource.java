package br.senai.sp.jandira.gamesapp.resource;

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
import br.senai.sp.jandira.gamesapp.resource.dto.TokenDto;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid Login login) {
		
		System.out.println("1 - " + login.toString());
		
		UsernamePasswordAuthenticationToken loginData = 
				new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
		
		try {
			Authentication authentication = authManager.authenticate(loginData);
			
			String token = tokenService.gerarToken(authentication);
			
			System.out.println(token);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
