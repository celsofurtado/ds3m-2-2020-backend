package br.senai.sp.jandira.gamesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.sp.jandira.gamesapp.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
	@Query("SELECT l FROM Like l WHERE l.gamePost.id = ?1")
	List<Like> findLikeByGamePost(Long gamePost);
	
	@Query("SELECT l FROM Like l WHERE l.gamePost.id = ?1 AND l.isLike is true")
	List<Like> findLikeTrueByGamePost(Long gamePost);
	
	@Query("SELECT l FROM Like l WHERE l.gamePost.id = ?1 AND l.isLike is false")
	List<Like> findLikeFalseByGamePost(Long gamePost);
	
	@Query("SELECT COUNT(l) FROM Like l WHERE l.gamePost.id = ?1")
	Integer countByPostId(Long codigoPost);

}
