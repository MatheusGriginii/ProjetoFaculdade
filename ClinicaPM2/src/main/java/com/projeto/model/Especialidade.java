package com.projeto.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Especialidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome; // Nome da especialidade médica

	// Relacionamento muitos-para-muitos com a entidade Paciente
	@ManyToMany
	private Set<Paciente> pacientes = new HashSet<>();

	public Especialidade() {}

	public Especialidade(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
}