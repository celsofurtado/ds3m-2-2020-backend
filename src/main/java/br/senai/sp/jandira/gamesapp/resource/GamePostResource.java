package br.senai.sp.jandira.gamesapp.resource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.model.GamePost;
import br.senai.sp.jandira.gamesapp.repository.GameRepository;
import br.senai.sp.jandira.gamesapp.resource.dto.GamePostDto;
import br.senai.sp.jandira.gamesapp.utils.Date;

@RestController
@RequestMapping("/api/posts")
public class GamePostResource {
	
	@Autowired
	private GameRepository gameRepository; 

	@GetMapping
	public List<GamePostDto> listar(){
		return GamePostDto.convertToDto(gameRepository.findAll());
	}
	
	@GetMapping("/gameposts")
	public List<GamePost> listarTodos(){
		return gameRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GamePost> listarJogoPorId(@PathVariable Long id){
		
		return this.gameRepository.findById(id)
				.map(game -> ResponseEntity.ok(game))
				.orElse(ResponseEntity.notFound().build());
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GamePost save(@Valid @RequestBody GamePost game) {
		
		// Determinando o horário de gravação do post		
		game.setPostDate(Date.getDateTime());
		
		System.out.println(game.getPostDate());
		
		return gameRepository.save(game);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		gameRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public GamePost atualizar(@PathVariable Long id, @RequestBody GamePost game) {
		GamePost gameAtual = gameRepository.findById(id).get();
		BeanUtils.copyProperties(game, gameAtual, "id");
		return gameRepository.save(gameAtual);
	}
	
}
