package br.senai.sp.jandira.gamesapp.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Login {

	private String email;
	private String password;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

//	public UsernamePasswordAuthenticationToken convertsss() {
//		return new UsernamePasswordAuthenticationToken(this.email, this.password);
//	}

}
