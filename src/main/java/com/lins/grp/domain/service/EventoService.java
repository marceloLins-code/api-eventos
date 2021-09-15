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
		Usuario usuarioAtual = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Não encontrado"));

		usuarioAtual.setAtivo(true);
		cadastrarUsr.isContemVaga();
		return eventoRepository.save(cadastrarUsr);
	}

	

	public void entrada(Evento evento) {
		Usuario usuarioValido = usuarioRepository.getById(evento.getId());

		if (usuarioValido == null || usuarioValido.isInativo() || !evento.isContemVaga()) {
			processarData(evento);
			throw new RuntimeException("usuario invalido");
		}
		evento.setUsuario(evento.getUsuario());
		eventoRepository.save(evento);
	}
	

		public void remover(Long cpf) {
		eventoRepository.deleteById(cpf);
		
	}
		
	
	public void processarData(Evento periodo){
		Long t1 = periodo.getDataCompra().getTime();
		Long t2 = periodo.getDataEvento().getTime();
		
		if (t1 > t2) {
			throw new RuntimeException("horário excedido!");
		}
		
	}

	/*
	 * // Listar as inscrições de um usuário; public Optional<Evento> listEvUs(Long
	 * uss) {
	 * 
	 * Usuario usr = usuarioRepository.getById(uss); return usuarioRepository.find
	 * 
	 * }
	 */

}
