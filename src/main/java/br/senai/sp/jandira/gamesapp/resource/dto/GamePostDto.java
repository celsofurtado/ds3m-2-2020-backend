package br.senai.sp.jandira.gamesapp.resource.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;

import br.senai.sp.jandira.gamesapp.model.GamePost;
import br.senai.sp.jandira.gamesapp.model.StatusGame;
import br.senai.sp.jandira.gamesapp.utils.Date;

public class GamePostDto {

	private Long id;
	private String userPostName;
	private String consoleName;
	private String consoleMaker;
	private String postTitle;
	private String postText;
	private int totalLikes;
	private int totalComments;
	private String postTime;
	private StatusGame statusGame;
	
	public GamePostDto() {}
	
	public GamePostDto(GamePost gamePost) {
		this.id = gamePost.getId();
		this.userPostName = gamePost.getUser().getName();
		this.consoleName = gamePost.getConsole().getConsoleName();
		this.consoleMaker = gamePost.getConsole().getConsoleMaker();
		this.postTitle = gamePost.getPostTitle();
		this.postText = gamePost.getPostText();
		this.totalLikes = getTotalLikes(gamePost.getId());
		this.totalComments = getTotalComments(gamePost.getId());
		this.postTime = Date.getDays(gamePost.getPostDate(), LocalDateTime.now());
		this.statusGame = gamePost.getStatus();
	}
	
	//**** PARA ACESSAR UM ENDPOINT, É NECESSÁRIO UM CLIENTE WEB, JÁ QUE O ENDPOINT ATENDE POR REQUISIÇÕES HTTP
	//**** O RestTemplate É UM CLIENTE WEB/REST QUE FAZ REQUISIÇÕES HTTP PARA UM ENDPOINT WEB, ASSIM COMO UM NAVEGADOR.
	public int getTotalLikes(Long id) {
		String uri = "http://localhost:8080/api/likes/{id}/totalcurtidas";
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("id", id);
		
		RestTemplate rest = new RestTemplate();
		String response = rest.getForObject(uri, String.class, params);
		
		return Integer.parseInt(response);
	}
	
	public int getTotalComments(Long id) {
		String uri = "http://localhost:8080/api/comments/{id}/totalcomments";
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("id", id);
		
		RestTemplate rest = new RestTemplate();
		String response = rest.getForObject(uri, String.class, params);
		
		return Integer.parseInt(response);
	}

	public Long getId() {
		return id;
	}
	
	public String getUserPostName() {
		return userPostName;
	}

	public String getConsoleName() {
		return consoleName;
	}

	public String getConsoleMaker() {
		return consoleMaker;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public String getPostText() {
		return postText;
	}

	public int getTotalLikes() {
		return totalLikes;
	}
	
	public int getTotalComments() {
		return totalComments;
	}

	public String getPostTime() {
		return postTime;
	}
	
	public StatusGame getStatusGame() {
		return statusGame;
	}

	public static List<GamePostDto> convertToDto(List<GamePost> gamePosts) {
		
		return gamePosts.stream().map(GamePostDto::new).collect(Collectors.toList());
	}

}
