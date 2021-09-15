package com.lins.grp.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.grp.domain.model.Evento;
import com.lins.grp.domain.repository.EventoRepository;
import com.lins.grp.domain.repository.UsuarioRepository;
import com.lins.grp.domain.service.EventoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/eventos")
public class EventoController {

	private EventoRepository eventoRepository;

	private EventoService eventoService;

	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Evento> listarEventos() {
		return eventoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> addEvt(@RequestBody Evento evento) {
		eventoService.eventoCriado(evento);
		return ResponseEntity.ok().body(evento);
	}

	@PostMapping("/{criar}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> adicionarUsuario(@RequestBody Evento criar) {
		eventoService.usuarioSalvo(criar);
		return ResponseEntity.ok().body(criar);
	}

	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> usuarioExcluir(@PathVariable Long cpf) {
		eventoService.remover(cpf);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/entrada")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> acessarEvento(@RequestBody Evento eventoNovo) {
		eventoService.entrada(eventoNovo);	
		
		// BeanUtils.copyProperties("", "", "id");
		return ResponseEntity.ok(eventoNovo);
		

	}
	
	@GetMapping("/cpf")
	public List<Evento> listarIns( String nome, Long cpf) {
		
		return eventoRepository.streamByNomeContainingAndUsuarioCpf(nome, cpf);
	}
	
	
	
	/*
	 * @GetMapping("/cpf/nome")
	 *  public List<Evento> list(Long cpf) {
	 * 
	 * 
	 * }
	 */

}