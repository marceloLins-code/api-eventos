package com.lins.grp.domain.service;

import org.springframework.stereotype.Service;

import com.lins.grp.domain.model.Usuario;
import com.lins.grp.domain.repository.EventoRepository;
import com.lins.grp.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private EventoRepository eventoRepository;

	public Usuario usuarioCriado(Usuario usuario) {

		boolean cpflEmUso = usuarioRepository.findById(usuario.getCpf()).stream()
				.anyMatch(clienteExistente -> clienteExistente.equals(usuario));
		if (cpflEmUso) {
			throw new RuntimeException("Usuario ja existente!");
		}		
		
		return usuarioRepository.save(usuario);
	}
	
	

	

	/*
	 * public void excluirUsuario(Long cpf) { try {
	 * usuarioRepository.deleteById(cpf);
	 * 
	 * } catch (DataIntegrityViolationException e) { throw new
	 * EntidadeEmUsoException(String.format("Usuario de cpf %d em uso", cpf)); }
	 * 
	 * }
	 */

}
