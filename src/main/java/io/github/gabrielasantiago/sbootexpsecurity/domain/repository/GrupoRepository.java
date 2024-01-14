package io.github.gabrielasantiago.sbootexpsecurity.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String>{
	
	Optional<Grupo> findByNome(String nome);
}
