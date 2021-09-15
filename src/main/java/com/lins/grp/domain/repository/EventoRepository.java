package com.lins.grp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.grp.domain.model.Evento;




@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	List<Evento> streamByNomeContainingAndUsuarioCpf(String nome,Long cpf);
	

}
