package com.projeto.controller;

import com.projeto.model.Paciente;
import com.projeto.model.Especialidade;
import com.projeto.model.Consulta;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class ConsultaController {

	// metodo para marcar consulta de um paciente com uma especialidade
	public static void marcarConsulta(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		System.out.println("Lista de pacientes:");
		PacienteController.listarPacientes();
		System.out.print("Digite o ID do paciente que deseja agendar: ");
		Long pacienteId = scanner.nextLong();

		System.out.println("Lista de especialidades:");
		EspecialidadeController.listarEspecialidades();
		System.out.print("Digite o ID da especialidade: ");
		Long especialidadeId = scanner.nextLong();

		scanner.nextLine(); // Limpa o buffer do scanner

		// Procurar paciente e especialidade no banco
		Paciente paciente = em.find(Paciente.class, pacienteId);
		Especialidade especialidade = em.find(Especialidade.class, especialidadeId);

		if (paciente == null || especialidade == null) {
			System.out.println("Paciente ou especialidade não encontrada. Cancelando...");
			em.close();
			return;
		}

		// Criar e salvar consulta
		em.getTransaction().begin();
		Consulta consulta = new Consulta(paciente, especialidade);
		em.persist(consulta);
		em.getTransaction().commit();

		System.out.println("Consulta marcada com sucesso!");
		em.close();
	}

	// Metodo para listar todas as consultas
	public static void listarConsultas() {
		EntityManager em = JPAUtil.getEntityManager();

		List<Consulta> consultas = em.createQuery("FROM Consulta", Consulta.class).getResultList();
		consultas.forEach(c -> System.out.println(
				"Consulta ID: " + c.getId() +
						" | Paciente: " + c.getPaciente().getNome() +
						" | Especialidade: " + c.getEspecialidade().getNome() +
						" | Data: " + c.getData()
		));

		em.close();
	}

	// Metodo para buscar consultas pelo CPF do paciente
	public static void buscarPorCpf(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		System.out.print("Digite o CPF do paciente: ");
		String cpf = scanner.nextLine();

		// Busca consultas utilizando JPQL
		List<Consulta> consultas = em.createQuery(
						"SELECT c FROM Consulta c WHERE c.paciente.cpf = :cpf", Consulta.class)
				.setParameter("cpf", cpf)
				.getResultList();

		if (consultas.isEmpty()) {
			System.out.println("Nenhuma consulta encontrada para o CPF informado.");
		} else {
			consultas.forEach(c -> System.out.println(
					"Consulta ID: " + c.getId() +
							" | Paciente: " + c.getPaciente().getNome() +
							" | Especialidade: " + c.getEspecialidade().getNome() +
							" | Data: " + c.getData()
			));
		}

		em.close();
	}

	// metodo para cancelar uma consulta
	public static void cancelarConsulta(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		listarConsultas();
		System.out.print("Digite o ID da consulta que deseja cancelar: ");
		Long consultaId = scanner.nextLong();

		Consulta consulta = em.find(Consulta.class, consultaId);

		if (consulta != null) {
			em.getTransaction().begin();
			em.remove(consulta);
			em.getTransaction().commit();
			System.out.println("Consulta cancelada com sucesso!");
		} else {
			System.out.println("Consulta não encontrada.");
		}

		em.close();
	}

	// remove todos os dados do banco
	public static void limparTudo() {
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM Consulta").executeUpdate();
		em.createQuery("DELETE FROM Especialidade").executeUpdate();
		em.createQuery("DELETE FROM Paciente").executeUpdate();
		em.getTransaction().commit();

		System.out.println("Todos os dados foram excluídos.");
		em.close();
	}
}