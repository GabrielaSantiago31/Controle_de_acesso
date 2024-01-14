package io.github.gabrielasantiago.sbootexpsecurity.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import io.github.gabrielasantiago.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.gabrielasantiago.sbootexpsecurity.domain.security.IdentificacaoUsuario;



@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		var login = authentication.getName();
		var senha = (String) authentication.getCredentials();
		
		String loginMaster = "master";
		String senhaMaster = "@321";
		
		if(loginMaster.equals(login) && senhaMaster.equals(senha)) {
			IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
					"Sou Master", 
					"Master", 
					loginMaster,List.of("ADMIN"));
			
			
			return new CustomAuthentication(identificacaoUsuario);
			
			
			//return new UsernamePasswordAuthenticationToken("Sou master", null, 
					//List.of(new SimpleGrantedAuthority("ADMIN")));
		//passe null ao invés de colocar a senha do usuário, pois, se colocar, a mesma estará vulnerável.
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
