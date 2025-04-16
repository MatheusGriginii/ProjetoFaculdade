package com.projeto.controller;

import com.projeto.model.Paciente;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class PacienteController {

	// Método para cadastrar um novo paciente
	public static void cadastrarPaciente(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		System.out.print("Nome do paciente: ");
		String nome = scanner.nextLine();

		System.out.print("CPF do paciente: ");
		String cpf = scanner.nextLine();

		System.out.print("E-mail do paciente: ");
		String email = scanner.nextLine();

		System.out.print("Telefone do paciente: ");
		String telefone = scanner.nextLine();

		em.getTransaction().begin();
		em.persist(new Paciente(nome, cpf, email, telefone));
		em.getTransaction().commit();

		System.out.println("Paciente cadastrado com sucesso!");
		em.close();
	}

	// Método para listar todos os pacientes cadastrados
	public static void listarPacientes() {
		EntityManager em = JPAUtil.getEntityManager();

		List<Paciente> pacientes = em.createQuery("FROM Paciente", Paciente.class).getResultList();
		pacientes.forEach(p -> System.out.println(
				p.getId() + " - " + p.getNome() +
						" | CPF: " + p.getCpf() +
						" | E-mail: " + p.getEmail()
		));

		em.close();
	}

	// Método para editar o nome de um paciente
	public static void editarNomePaciente(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		listarPacientes();
		System.out.print("Digite o ID do paciente que deseja editar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		Paciente paciente = em.find(Paciente.class, id);

		if (paciente != null) {
			System.out.print("Novo nome para o paciente: ");
			String novoNome = scanner.nextLine();

			em.getTransaction().begin();
			paciente.setNome(novoNome);
			em.getTransaction().commit();

			System.out.println("Nome do paciente atualizado com sucesso!");
		} else {
			System.out.println("Paciente não encontrado.");
		}

		em.close();
	}

	// Método para buscar pacientes pelo nome
	public static void buscarnome(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		System.out.print("Digite parte do nome do paciente: ");
		String termo = scanner.nextLine();

		if (termo.isEmpty()) {
			System.out.println("Por favor, insira um termo de busca válido.");
			return;
		}

		// Busca os pacientes cujo nome contém o termo informado
		List<Paciente> pacientes = em.createQuery(
						"SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(:termo)", Paciente.class)
				.setParameter("termo", "%" + termo + "%")
				.getResultList();

		if (pacientes.isEmpty()) {
			System.out.println("Nenhum paciente encontrado com o termo informado.");
		} else {
			pacientes.forEach(p -> System.out.println(
					"ID: " + p.getId() +
							" | Nome: " + p.getNome() +
							" | CPF: " + p.getCpf()
			));
		}

		em.close();
	}
}