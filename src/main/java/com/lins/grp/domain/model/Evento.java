package com.lins.grp.domain.model;



import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Table(name ="evento")
public class Evento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@JoinColumn(nullable = false)
	private String nome;
	
	@JoinColumn(nullable = false)
	private int vaga;
	
	@ManyToOne
	private Usuario usuario;	
	
	private Date dataCompra;
	
	private Date dataEvento;
	
	public Evento() {
		
	}
	
	public boolean isContemVaga() {
		if (vaga <1) {
			throw new RuntimeException("vagas esgotadas!");
		}
		this.vaga = vaga -1;
		return true; 
		
	}
	public void eventoIniciado() {
		if (getDataCompra().getTime() > getDataEvento().getTime()) {
			throw new RuntimeException("evento ja iniciado!");
		}
	}
	
	
	

}


