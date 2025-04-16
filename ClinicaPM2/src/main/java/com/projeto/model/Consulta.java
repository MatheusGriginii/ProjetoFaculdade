package com.projeto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Paciente paciente; // Relacionamento com o paciente

	@ManyToOne
	private Especialidade especialidade; // Relacionamento com a especialidade

	private LocalDateTime data; // Data e hora da consulta

	public Consulta() {}

	public Consulta(Paciente paciente, Especialidade especialidade) {
		this.paciente = paciente;
		this.especialidade = especialidade;
		this.data = LocalDateTime.now(); // Define o momento atual por padrão
	}

	public Long getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}