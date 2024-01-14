package io.github.gabrielasantiago.sbootexpsecurity.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Optional<Usuario> findByLogin(String login);
}
