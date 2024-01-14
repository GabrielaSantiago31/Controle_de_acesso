package io.github.gabrielasantiago.sbootexpsecurity.api.dto;

import java.util.List;

import io.github.gabrielasantiago.sbootexpsecurity.domain.entity.Usuario;
import lombok.Data;


@Data
public class CadastroUsuarioDTO {
	
	private Usuario usuario;
	private List<String> permissoes;
}
