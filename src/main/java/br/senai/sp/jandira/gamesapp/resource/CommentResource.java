package br.senai.sp.jandira.gamesapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.model.Comment;
import br.senai.sp.jandira.gamesapp.repository.CommentRepository;
import br.senai.sp.jandira.gamesapp.resource.dto.CommentDto;
import br.senai.sp.jandira.gamesapp.utils.Date;

@RestController
@RequestMapping("/api/comments")
public class CommentResource {

	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping
	public List<CommentDto> getComments() {
		return CommentDto.convertToDto(commentRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public Comment getComments(@PathVariable Long id) {
		return commentRepository.findById(id).get();
	}
	
	@GetMapping("/{post}/totalcomments")
	public int getTotalCommentByPost(@PathVariable Long post) {
		return commentRepository.countByPostId(post);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comment saveComment(@RequestBody Comment comment) {
		comment.setCommentDate(Date.getDateTime());
		return commentRepository.save(comment);
	}
	
}
