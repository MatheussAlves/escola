package com.hepta.escola.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hepta.escola.enums.Ensino;

@Entity
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ALUNO")
	private Integer id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="IDADE")
	private Integer idade;
	
	@Column(name="SERIE")
	private String serie;
	
	@Enumerated(EnumType.STRING)
	@Column(name="NIVEL_ENSINO")
	private Ensino ensino;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Ensino getEnsino() {
		return ensino;
	}

	public void setEnsino(Ensino ensino) {
		this.ensino = ensino;
	}
	
	
	
	
	
	
}
