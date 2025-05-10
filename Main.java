package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

// Ponto de entrada da nossa aplicação
@SpringBootApplication
public class HotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }
}

// Controlador responsável por gerenciar as requisições da aplicação
@Controller
@RequestMapping("/hotel")
class HotelController {
    // Definição do login fixo (por enquanto sem banco de dados)
    private static final String ADMIN_USER = "Admin";
    private static final String ADMIN_PASS = "Admin";
    
    // Serviço que gerencia os hóspedes
    @Autowired
    private HospedeService hospedeService;

    // Verifica se o usuário e a senha são válidos
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
            return "redirect:/hotel/dashboard"; // Redireciona para a dashboard
        }
        return "redirect:/login.html"; // Se errado, volta para a tela de login
    }

    // Página da dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("hospedes", hospedeService.listarHospedes()); // Adiciona a lista de hóspedes
        return "dashboard";
    }

    // Cadastro de um novo hóspede
    @PostMapping("/cadastrarHospede")
    public String cadastrarHospede(@RequestParam String nome, @RequestParam String cpf, @RequestParam String dataNascimento) {
        hospedeService.cadastrarHospede(new Hospede(nome, cpf, dataNascimento)); // Salva no banco de dados
        return "redirect:/hotel/dashboard"; // Atualiza a página
    }
}

// Representação de um hóspede no banco de dados
import jakarta.persistence.*;
@Entity
class Hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;

    public Hospede() {} // Construtor vazio necessário para JPA

    public Hospede(String nome, String cpf, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters Instacionar
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getDataNascimento() { return dataNascimento; }
    
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}

// Interface para comunicação com o banco de dados
import org.springframework.data.jpa.repository.JpaRepository;
interface HospedeRepository extends JpaRepository<Hospede, Long> {}

// Serviço que encapsula a lógica de negócios dos hóspedes
import org.springframework.stereotype.Service;
@Service
class HospedeService {
    @Autowired
    private HospedeRepository hospedeRepository;

    public List<Hospede> listarHospedes() {
        return hospedeRepository.findAll(); // Retorna todos os hóspedes cadastrados
    }

    public void cadastrarHospede(Hospede hospede) {
        hospedeRepository.save(hospede); // Salva um novo hóspede
    }
}