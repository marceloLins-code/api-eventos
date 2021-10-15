package com.lins.grp.domain.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lins.grp.domain.exceptions.EntidadeEmUsoException;
import com.lins.grp.domain.model.Evento;
import com.lins.grp.domain.model.Usuario;
import com.lins.grp.domain.repository.EventoRepository;
import com.lins.grp.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventoService {

	public EventoRepository eventoRepository;

	public UsuarioRepository usuarioRepository;

	public Evento criarEvento(Evento evento) {
		return eventoRepository.save(evento);

	}

	public Evento inscreverUsuario(Long cpfUsuario, Long eventoId) {
		Evento eventoAtual = eventoRepository.findById(eventoId)
				.orElseThrow(() -> new RuntimeException(" Id Não encontrado"));

		Usuario usuarioAtual = usuarioRepository.findById(cpfUsuario)
				.orElseThrow(() -> new RuntimeException(" Id Usuario não encontrado"));

		eventoAtual.setUsuario(usuarioAtual);
		// usuarioAtual.setAtivo(true);
		eventoAtual.isContemVaga();
		return eventoRepository.save(eventoAtual);

	}

	public Evento cancelarInscricao(Long cpfUsuario, Long eventoId) {

		Evento eventoAtual = eventoRepository.findById(eventoId)
				.orElseThrow(() -> new RuntimeException(" Id Não encontrado"));

		Usuario usuarioAtual = usuarioRepository.findById(cpfUsuario)
				.orElseThrow(() -> new RuntimeException(" Id Usuario não encontrado"));

		eventoAtual.setUsuario(null);
		usuarioAtual.setAtivo(false);
		eventoAtual.setVaga(+1);
		return eventoRepository.save(eventoAtual);

	}

	public Evento acessoEvento(Long eventoId, Long cpfUsuario) {
		Evento eventoAtual = eventoRepository.findById(eventoId)
				.orElseThrow(() -> new RuntimeException(" Id Não encontrado"));

		Usuario usuarioAtual = usuarioRepository.findById(cpfUsuario)
				.orElseThrow(() -> new RuntimeException(" Id Usuario não encontrado"));

		if (eventoAtual.getUsuario().getCpf().equals(usuarioAtual.getCpf())) {
			usuarioAtual.setAtivo(true);

		}
		return eventoRepository.save(eventoAtual);
	}

	public List<Evento> listarEventos(Long cpf) {

		return eventoRepository.streamByUsuarioCpf(cpf);
	}
	
	//listar usuarios
	public Usuario listarUsuarios(Long id) {
		
		Optional<Usuario> usuariosList = usuarioRepository.findById(id);
		 
		usuariosList.get().getEventoList().forEach(e -> e.getUsuario());
		 return usuariosList.get();
	}


	public void excluirUsuario(Evento evento) {
		Long usuarioId = evento.getUsuario().getCpf();
		Usuario usuarioAtual = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Não encontrado"));
		try {
			usuarioAtual.setAtivo(false);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Usuario de cpf %d em uso", usuarioAtual));
		}

	}

	public void atualizarPessoa(Long id, Boolean ativo) {
		Evento eventoAtual = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));

		eventoAtual.isContemVaga();
		eventoAtual.getUsuario().setAtivo(ativo);
		eventoRepository.save(eventoAtual);

	}

}
