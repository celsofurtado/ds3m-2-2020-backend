package br.senai.sp.jandira.gamesapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.repository.CommentRepository;
import br.senai.sp.jandira.gamesapp.resource.dto.CommentDto;

@RestController
@RequestMapping("/api/comments")
public class CommentResource {

	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping
	public List<CommentDto> getComments() {
		return CommentDto.convertToDto(commentRepository.findAll());
	}
	
	@GetMapping("/{post}/totalcomments")
	public int getTotalCommentByPost(@PathVariable Long post) {
		return commentRepository.countByPostId(post);
	}
	
}
