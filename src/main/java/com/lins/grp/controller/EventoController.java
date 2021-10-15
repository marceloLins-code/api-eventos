package com.lins.grp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.grp.domain.model.Evento;
import com.lins.grp.domain.model.Usuario;
import com.lins.grp.domain.repository.EventoRepository;
import com.lins.grp.domain.service.EventoService;
import com.lins.grp.domain.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/eventos")
public class EventoController {

	private EventoRepository eventoRepository;

	private EventoService eventoService;
	
	
	
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> eventoCriar(@RequestBody Evento evento) {
		eventoService.criarEvento(evento);
		
		return ResponseEntity.ok().body(evento);
	}

	@PostMapping("/inscrever/{cpf}/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> increverUsuario(@PathVariable Long cpf, @PathVariable Long id) {
		Evento usuarioValido = eventoService.inscreverUsuario(cpf, id);

		return ResponseEntity.ok(usuarioValido);
	}

	@PostMapping("/acessar/{id}/{cpf}")
	public ResponseEntity<Evento> acessarEvento(@PathVariable Long id, @PathVariable Long cpf) {
		Evento usuarioEmAcesso = eventoService.acessoEvento(id, cpf);
		return ResponseEntity.ok(usuarioEmAcesso);
	}

	@GetMapping()
	public List<Evento> listarEventos() {
		return eventoRepository.findAll();
	}
	
	
	@GetMapping("/{cpf}")
	public List<Evento> listarEventos(Long cpf) {
		
		return eventoService.listarEventos( cpf);
	}
	
	

	@GetMapping("buscar/por-cpf")
	public List<Evento> buscarPorNome(String nome, Long cpfUsuario) {
		
		return eventoRepository.buscarPorNome(nome, cpfUsuario);		
		
	}
	
	
	
	@GetMapping("listUsuario/{cpf}")
	public ResponseEntity<Usuario> listaDeUsuarios(Long cpf) {
		Usuario usuarioEmAcesso = eventoService.listarUsuarios(cpf);
		return ResponseEntity.ok().body(usuarioEmAcesso);
		
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void usuarioSalvo(@PathVariable Long id, @RequestBody Boolean ativo) {

		eventoService.atualizarPessoa(id, ativo);
	}



}