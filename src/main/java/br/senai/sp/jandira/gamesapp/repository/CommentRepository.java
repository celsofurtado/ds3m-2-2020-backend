package br.senai.sp.jandira.gamesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.jandira.gamesapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.gamePost.id = ?1")
	Integer countByPostId(Long gamePostId);

}
