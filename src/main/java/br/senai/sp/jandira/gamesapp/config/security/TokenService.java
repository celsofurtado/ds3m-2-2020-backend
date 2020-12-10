package br.senai.sp.jandira.gamesapp.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.senai.sp.jandira.gamesapp.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${instagames.jwt.expiration}")
	private String expiration;
	
	@Value("${instagames.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		Date hoje = new Date();
		Date dateExpiration = new Date(hoje.getTime() + Long.parseLong(this.expiration));
		
		return Jwts.builder()
				.setIssuer("API Instagames")
				.setSubject(user.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

}
