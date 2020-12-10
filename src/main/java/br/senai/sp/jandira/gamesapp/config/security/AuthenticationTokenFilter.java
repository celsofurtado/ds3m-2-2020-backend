package br.senai.sp.jandira.gamesapp.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.senai.sp.jandira.gamesapp.model.User;
import br.senai.sp.jandira.gamesapp.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	private UserRepository userRepository;
	
	public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
		super();
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Recuperar o token do header
		String token = retrieveToken(request);
		
		// Verificar se o token é válido
		boolean validToken = tokenService.isValidToken(token);
		
		if (validToken) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void authenticateClient(String token) {
		Long idUser = tokenService.getUserId(token);
		User user = userRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		} 
		
		return token.substring(7, token.length());
	}

}
