package com.lins.grp.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;


@JsonRootName("usuario")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@EqualsAndHashCode.Include
	@Id
	@Column(nullable = false)	
	private Long cpf;	
	
	@Column(name = "nome", nullable = false)
	private String nome;	
	
	
	private Boolean ativo;
		
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
		
	}
	
	
	
	
	
}
