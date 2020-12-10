package br.senai.sp.jandira.gamesapp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Responsável pelas configurações de autenticação (Controle de acesso e login)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	
	// Responsável pela configuração de Autorização (quem pode fazer o que)
	// Aqui definimos quais são os endpoints que são públicos e quais precisam de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests()
//		.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/consoles").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/consoles/*").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/posts").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/likes/*/totalcurtidas").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/comments/*/totalcomments").permitAll()
//		.anyRequest().authenticated()
//		.and().csrf().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().antMatchers("/api/*").permitAll();
		
	}
	
	// Responsável por configurar acesso aos recursos estáticos (css, js, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123"));
//	}
	
}
