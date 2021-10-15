package com.lins.grp.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.mapping.Array;

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
	@Column(nullable = true)	
	private Long cpf;	
	
	@Column(length = 30)
	private String nome;	
	
	@Column()
	private Boolean ativo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Evento> eventoList = new ArrayList();
		
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
		
	}
	
	
	
	
	
}
