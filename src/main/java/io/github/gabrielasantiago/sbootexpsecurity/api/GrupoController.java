package io.github.gabrielasantiago.sbootexpsecurity.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.Grupo;
import io.github.gabrielasantiago.sbootexpsecurity.domain.repository.GrupoRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	private final GrupoRepository repository = null;
	
	@PostMapping
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo){
		repository.save(grupo);
		return ResponseEntity.ok(grupo);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Grupo>> listar(){
	
		return ResponseEntity.ok(repository.findAll());
	}
}
