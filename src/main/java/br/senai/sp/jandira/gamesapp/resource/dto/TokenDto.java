package br.senai.sp.jandira.gamesapp.resource.dto;

public class TokenDto {

	private Long id;
	private String token;
	private String tipo;
	private String name;
	private String email;

	public TokenDto(String token, String tipo, String name, String email, Long id) {
		this.token = token;
		this.tipo = tipo;
		this.name = name;
		this.email = email;
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Long getId() {
		return id;
	}

}
