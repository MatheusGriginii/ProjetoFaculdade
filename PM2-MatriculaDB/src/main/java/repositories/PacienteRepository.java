package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

import com.projeto.model.Paciente;

// Classe responsável pela comunicação com o banco de dados relacionada aos pacientes
public class PacienteRepository {

	// instancia a fabrica de EntityManager, responsavel por criar as conexões com o banco de dados
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pacientePU");

	// metodo para buscar pacientes pelo nome, com base em um termo fornecido
	public List<Paciente> buscarPorNome(String termo) {
		// Cria uma instância do EntityManager para fazer as consultas no banco de dados
		EntityManager em = emf.createEntityManager();

		// Executa a consulta JPQL para buscar pacientes cujo nome contenha o termo fornecido
		// O operador LIKE é usado para buscar padrões no nome
		List<Paciente> resultado = em.createQuery("SELECT p FROM Paciente p WHERE p.nome LIKE :nome", Paciente.class)
				.setParameter("nome", "%" + termo + "%") // O parâmetro %...% permite buscar qualquer nome que contenha o termo
				.getResultList(); // Retorna a lista de pacientes encontrados

		// Fecha o EntityManager após a execução da consulta
		em.close();
		return resultado; // Retorna a lista de pacientes encontrados
	}
}