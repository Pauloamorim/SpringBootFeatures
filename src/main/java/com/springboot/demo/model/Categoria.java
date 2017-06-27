package com.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public  class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private @Getter @Setter Integer id;
	
	@Column
	@NotBlank(message="O campo descrição deve ser preenchido")
	private @Getter @Setter String descricao;
	
}
