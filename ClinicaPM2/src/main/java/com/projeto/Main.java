package com.projeto;

// Importações dos controladores e utilitários
import com.projeto.controller.PacienteController;
import com.projeto.controller.EspecialidadeController;
import com.projeto.controller.ConsultaController;
import com.projeto.model.Paciente;
import com.projeto.util.JPAUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static final Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário

	public static void main(String[] args) {
		int opcao;
		// Estrutura de repetição do menu principal
		do {
			// Exibição do menu de opções
			System.out.println("\n--- MENU ---");
			System.out.println("1. Cadastrar paciente");
			System.out.println("2. Listar pacientes");
			System.out.println("3. Cadastrar especialidade médica");
			System.out.println("4. Listar especialidades");
			System.out.println("5. Marcar consulta");
			System.out.println("6. Listar consultas");
			System.out.println("7. Editar nome de paciente");
			System.out.println("8. Cancelar consulta");
			System.out.println("9. Buscar consultas por CPF do paciente");
			System.out.println("10. Limpar todos os dados");
			System.out.println("11. Pesquisar paciente pelo nome");
			System.out.println("0. Sair");
			System.out.print("Escolha: ");

			opcao = scanner.nextInt(); // Lê a opção digitada
			scanner.nextLine(); // Limpa o buffer de entrada (evita erros de leitura)

			// Estrutura de decisão com switch para executar a ação escolhida
			switch (opcao) {
				case 1 -> PacienteController.cadastrarPaciente(scanner); // Cadastrar novo paciente
				case 2 -> PacienteController.listarPacientes(); // Listar todos os pacientes cadastrados
				case 3 -> EspecialidadeController.cadastrarEspecialidade(scanner); // Cadastrar uma nova especialidade
				case 4 -> EspecialidadeController.listarEspecialidades(); // Listar todas as especialidades
				case 5 -> ConsultaController.marcarConsulta(scanner); // Marcar uma consulta
				case 6 -> ConsultaController.listarConsultas(); // Listar todas as consultas
				case 7 -> PacienteController.editarNomePaciente(scanner); // Editar o nome de um paciente
				case 8 -> ConsultaController.cancelarConsulta(scanner); // Cancelar uma consulta
				case 9 -> ConsultaController.buscarPorCpf(scanner); // Buscar consultas por CPF do paciente
				case 10 -> ConsultaController.limparTudo(); // Limpar todos os dados do banco
				case 11 -> PacienteController.buscarnome(scanner); // Buscar paciente pelo nome
				case 0 -> System.out.println("Encerrando..."); // Encerra o programa
				default -> System.out.println("Opção inválida."); // Trata entradas inválidas
			}

		} while (opcao != 0); // O loop continua até o usuário escolher sair

		JPAUtil.close(); // Encerra a conexão com o JPA/Hibernate
	}

	// metodo auxiliar para testes
	public static void main1(String[] args) {
		// Exemplo de uso: buscar pacientes pelo nome
		List<Paciente> pacientes = List.of(); // Simulação de lista vazia
		// Preenchendo lista para teste
		if (!pacientes.isEmpty()) {
			pacientes.forEach(p -> System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome()));
		}
	}
}