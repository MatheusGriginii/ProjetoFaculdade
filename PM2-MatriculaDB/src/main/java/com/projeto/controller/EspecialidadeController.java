package com.projeto.controller;

import com.projeto.model.Especialidade;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class EspecialidadeController {

	// Método para cadastrar uma nova especialidade
	public static void cadastrarEspecialidade(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager(); // Gerenciador de entidades

		String nome;
		// Loop para garantir que o nome inserido contenha apenas letras (com acentuação) e espaços
		do {
			System.out.print("Digite o nome da especialidade (apenas letras): ");
			nome = scanner.nextLine();
		} while (!nome.matches("[a-zA-ZÀ-ÿ ]+")); // Valida apenas letras (inclusive acentuadas)

		// Início da transação para salvar a especialidade
		em.getTransaction().begin();
		em.persist(new Especialidade(nome)); // Persiste a especialidade no banco
		em.getTransaction().commit(); // Finaliza a transação
		em.close(); // Fecha o EntityManager

		System.out.println("Especialidade cadastrada com sucesso!");
	}

	// Método para listar todas as especialidades cadastradas
	public static void listarEspecialidades() {
		EntityManager em = JPAUtil.getEntityManager();

		// Consulta JPQL para buscar todas as especialidades cadastradas
		List<Especialidade> especialidades = em.createQuery("FROM Especialidade", Especialidade.class).getResultList();

		// Exibe as especialidades encontradas
		especialidades.forEach(e -> System.out.println(e.getId() + " - " + e.getNome()));

		em.close(); // Fecha o EntityManager
	}
}