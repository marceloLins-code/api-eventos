package com.lins.grp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lins.grp.domain.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	@Query("from Evento where nome like %:nome% and usuario.cpf = :cpf")
	List<Evento> buscarPorNome(String nome,@Param("cpf") Long cpf);

	List<Evento> streamByUsuarioCpf(Long cpf);
	
	

}
