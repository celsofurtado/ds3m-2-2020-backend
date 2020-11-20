package br.senai.sp.jandira.gamesapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.model.Like;
import br.senai.sp.jandira.gamesapp.repository.LikeRepository;

@RestController
@RequestMapping("/api/likes")
public class LikeResource {

	@Autowired
	private LikeRepository lr;
	
	@GetMapping("/{post}")
	public List<Like> getLikes(@PathVariable Long post) {
		
		return lr.findLikeByGamePost(post);
		
	}
	
	@GetMapping("/{post}/true")
	public List<Like> getLikesTrue(@PathVariable Long post) {
		System.out.println("************************ TRUE");
		return lr.findLikeTrueByGamePost(post);
	}
	
	@GetMapping("/{post}/false")
	public List<Like> getLikesFalse(@PathVariable Long post) {
		return lr.findLikeFalseByGamePost(post);
	}
	
	@GetMapping("/{post}/totalcurtidas")
	public Integer getTotalCurtidas(@PathVariable Long post) {
		
		Integer totalCurtidas = lr.countByPostId(post);
		
		return totalCurtidas;
	}
}
