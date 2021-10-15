package com.lins.grp.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	private Long id;

	@Column()
	private String nome;

	// @JsonIgnore
	@Column()
	private int vaga;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Usuario usuario;

	private Date dataCompra;

	private Date dataEvento;

	public Evento() {

	}

	public boolean isContemVaga() {
		if (this.vaga < 1) {
			return false;

		}
		this.vaga = vaga - 1;
		return true;
	}

	@JsonIgnore
	@Transient
	public boolean eventoIniciado() {
		if (getDataCompra().getTime() > getDataEvento().getTime()) {
			throw new RuntimeException("evento ja iniciado!");
		}
		return false;
	}

}
