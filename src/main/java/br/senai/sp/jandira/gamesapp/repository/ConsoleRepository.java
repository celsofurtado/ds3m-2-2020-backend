package br.senai.sp.jandira.gamesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.jandira.gamesapp.model.Console;

public interface ConsoleRepository extends JpaRepository<Console, Long> {

}
