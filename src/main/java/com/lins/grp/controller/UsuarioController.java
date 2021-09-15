package com.lins.grp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.grp.domain.model.Usuario;
import com.lins.grp.domain.repository.UsuarioRepository;
import com.lins.grp.domain.service.UsuarioService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;
	
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@RequestBody Usuario usuario) {
		return usuarioService.usrCriado(usuario);
	}
	
	
	//extra
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> usrDelete(@PathVariable Long cpf) {
		usuarioService.remover(cpf);
		return ResponseEntity.noContent().build();
	}
	
	

}
