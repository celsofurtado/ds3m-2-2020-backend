package br.senai.sp.jandira.gamesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.jandira.gamesapp.model.GamePost;

public interface GameRepository extends JpaRepository<GamePost, Long> {

}
