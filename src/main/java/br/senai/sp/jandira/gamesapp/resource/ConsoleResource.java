package br.senai.sp.jandira.gamesapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.model.Console;
import br.senai.sp.jandira.gamesapp.repository.ConsoleRepository;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleResource {

	@Autowired
	private ConsoleRepository consoleRepository;

	@GetMapping
	public List<Console> getConsoles() {
		return consoleRepository.findAll();
	}

	@GetMapping("/{id}")
	public HttpEntity<Console> getConsole(@PathVariable Long id) {
		
		return this.consoleRepository.findById(id)
				.map(console -> ResponseEntity.ok(console))
				.orElse(ResponseEntity.notFound().build());
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Console save(@RequestBody Console console) {
		return consoleRepository.save(console);
	}

}
