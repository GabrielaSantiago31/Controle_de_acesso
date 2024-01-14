package io.github.gabrielasantiago.sbootexpsecurity.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.Grupo;
import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.Usuario;
import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.gabrielasantiago.sbootexpsecurity.domain.repository.GrupoRepository;
import io.github.gabrielasantiago.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import io.github.gabrielasantiago.sbootexpsecurity.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final GrupoRepository grupoRepository;
	private final UsuarioGrupoRepository usuarioGrupoRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario, List<String> grupos) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioRepository.save(usuario);
		
		List<UsuarioGrupo> listarUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
			Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
			if(possivelGrupo.isPresent()) {
				Grupo grupo = possivelGrupo.get();
				return new UsuarioGrupo(usuario, grupo);
			}
			return null;
		})
				.filter(grupo -> grupo != null)
				.collect(Collectors.toList());
		usuarioGrupoRepository.saveAll(listarUsuarioGrupo);
		
		return usuario;
	}
	
	public Usuario obterUsuarioComPermissoes(String login) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
		if(usuarioOptional.isEmpty()) {
			return null;
		}
		
		Usuario usuario = usuarioOptional.get();
		List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
		usuario.setPermissoes(permissoes);
		
		return usuario;
		
	}
	
}
