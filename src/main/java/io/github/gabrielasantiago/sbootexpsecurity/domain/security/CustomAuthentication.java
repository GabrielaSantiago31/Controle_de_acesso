package io.github.gabrielasantiago.sbootexpsecurity.domain.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthentication implements Authentication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final IdentificacaoUsuario identificacaoUsuario;
	
	public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
		if(identificacaoUsuario == null) {
			throw new ExceptionInInitializerError("Não é possível criar um custom authorization sem a identificação do usuário");
			
		}
		this.identificacaoUsuario = identificacaoUsuario;
	}

	@Override
	public String getName() {
		
		return this.identificacaoUsuario.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.identificacaoUsuario
				.getPermissoes()
				.stream()
				.map(permission -> new SimpleGrantedAuthority(permission))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {

		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
	
		return this.identificacaoUsuario;
	}

	@Override
	public boolean isAuthenticated() {
		
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new IllegalStateException("Não precisa chamar, já estamos autenticados");
		
	}

}
