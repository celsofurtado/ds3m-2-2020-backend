package br.senai.sp.jandira.gamesapp.test;

import br.senai.sp.jandira.gamesapp.model.GamePost;
import br.senai.sp.jandira.gamesapp.resource.dto.GamePostDto;

public class Teste {

	public static void main(String[] args) {
		
		GamePost p = new GamePost();
		p.setId(3L);
		GamePostDto dto = new GamePostDto();
		System.out.println(dto.getTotalLikes(p.getId()));

	}

}
