package com.lins.grp.domain.service;

import org.springframework.stereotype.Service;

import com.lins.grp.domain.model.Usuario;
import com.lins.grp.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {	
	
	private UsuarioRepository usuarioRepository;	
	
	
	// 2/7 Realizar a criação de um usuário;
	public Usuario usrCriado(Usuario usuario) {
		
		boolean cpflEmUso = usuarioRepository.findById(usuario.getCpf())
				.stream()
				.anyMatch(clienteExistente -> clienteExistente.equals(usuario));		
		if (cpflEmUso) {
			throw new RuntimeException("Usuario ja existente!");
			}
		
		return usuarioRepository.save(usuario);		
	}
	
	
	public void remover( Long cpf) {		
		usuarioRepository.deleteById(cpf);	
		throw new RuntimeException("Usuario criado!");
	
	}
	
	
	
	
	

}
