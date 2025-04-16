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

		try {
			em.getTransaction().begin();

			// Verificar se o CPF já está cadastrado
			boolean cpfExiste = !em.createQuery("SELECT p FROM Paciente p WHERE p.cpf = :cpf", Paciente.class)
					.setParameter("cpf", cpf)
					.getResultList()
					.isEmpty();

			if (cpfExiste) {
				System.out.println("Erro: Já existe um paciente cadastrado com esse CPF.");
				em.getTransaction().rollback();
				return;
			}

			// Persistindo o novo paciente
			em.persist(new Paciente(nome, cpf, email, telefone));
			em.getTransaction().commit();
			System.out.println("Paciente cadastrado com sucesso!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao cadastrar o paciente: " + e.getMessage());
		} finally {
			em.close();
		}
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

	public static void editarNomePaciente(Scanner scanner) {
	}

	public static void buscarnome(Scanner scanner) {
	}

	// Outros métodos permanecem os mesmos...
}