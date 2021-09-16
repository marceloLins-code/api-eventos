package com.lins.grp.domain.service;

import org.springframework.stereotype.Service;

import com.lins.grp.domain.model.Evento;
import com.lins.grp.domain.model.Usuario;
import com.lins.grp.domain.repository.EventoRepository;
import com.lins.grp.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventoService {

	private EventoRepository eventoRepository;

	private UsuarioRepository usuarioRepository;

	// criar evento
	public Evento eventoCriado(Evento evento) {
		return eventoRepository.save(evento);

	}

	// cadastrar usuario
	public Evento usuarioSalvo(Evento cadastrarUsr) {
		Long usuarioId = cadastrarUsr.getUsuario().getCpf();
		Usuario usuarioAtual = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Não encontrado"));

		cadastrarUsr.eventoIniciado();
		cadastrarUsr.isContemVaga();
		usuarioAtual.setAtivo(true);
		return eventoRepository.save(cadastrarUsr);
	}

	public void entrada(Evento evento) {
		Usuario usuarioValido = usuarioRepository.getById(evento.getId());

		if (usuarioValido == null || usuarioValido.isInativo() || !evento.isContemVaga()) {

			throw new RuntimeException("usuario invalido");
		}

		eventoRepository.save(evento);
	}

	public void cancelarEvento(Long cpf) {
		Usuario idUsuario = usuarioRepository.getById(cpf);
		if (!idUsuario.isInativo()) {
			throw new RuntimeException("usuario ativo");
		}
		eventoRepository.deleteById(idUsuario.getCpf());
	}

	/*
	 * // Listar as inscrições de um usuário; public Optional<Evento> listEvUs(Long
	 * uss) {
	 * 
	 * Usuario usr = usuarioRepository.getById(uss); return usuarioRepository.find }
	 */

}
