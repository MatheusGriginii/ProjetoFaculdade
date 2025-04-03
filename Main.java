package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Classe principal que inicia a aplicação Spring Boot
@SpringBootApplication
public class HotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }
}

// Controlador que gerencia as rotas do sistema
@Controller
@RequestMapping("/hotel")
class HotelController {
    // Definição do login fixo
    private static final String ADMIN_USER = "Admin";
    private static final String ADMIN_PASS = "Admin";
    
    // Injeção do serviço de hóspede
    @Autowired
    private HospedeService hospedeService;

    // Endpoint para validar o login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Se o usuário e senha forem corretos, redireciona para a dashboard
        if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
            return "redirect:/hotel/dashboard";
        }
        // Caso contrário, redireciona de volta para a tela de login
        return "redirect:/login.html";
    }

    // Endpoint para exibir a dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Envia a lista de hóspedes para a página da dashboard
        model.addAttribute("hospedes", hospedeService.listarHospedes());
        return "dashboard";
    }

    // Endpoint para cadastrar um novo hóspede
    @PostMapping("/cadastrarHospede")
    public String cadastrarHospede(@RequestParam String nome, @RequestParam String cpf, @RequestParam String dataNascimento) {
        // Salva o hóspede no banco de dados
        hospedeService.cadastrarHospede(new Hospede(nome, cpf, dataNascimento));
        // Redireciona de volta para a dashboard após o cadastro
        return "redirect:/hotel/dashboard";
    }
}

// Entidade que representa um Hóspede
import jakarta.persistence.*;
@Entity
class Hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;

    // Construtor padrão necessário para JPA
    public Hospede() {}

    // Construtor para inicializar um hóspede
    public Hospede(String nome, String cpf, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Métodos getters e setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getDataNascimento() { return dataNascimento; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}

// Repositório para interagir com o banco de dados
import org.springframework.data.jpa.repository.JpaRepository;
interface HospedeRepository extends JpaRepository<Hospede, Long> {}

// Serviço para lidar com a lógica de negócios dos hóspedes
import org.springframework.stereotype.Service;
import java.util.List;
@Service
class HospedeService {
    @Autowired
    private HospedeRepository hospedeRepository;

    public List<Hospede> listarHospedes() {
        return hospedeRepository.findAll();
    }

    public void cadastrarHospede(Hospede hospede) {
        hospedeRepository.save(hospede);
    }
}
