# 🏥 Sistema clinica

O **clinica** é uma aplicação projetada para realizar o gerenciamento de pacientes, especialidades médicas e consultas. Com uma interface simples e interativa, o sistema permite que clínicas médicas organizem de forma eficiente suas operações, desde o cadastro de pacientes até o agendamento e cancelamento de consultas.

---

## 🚀 Funcionalidades Principais

- **Gerenciamento de Pacientes**  
  Cadastro, listagem, edição de informações e busca de pacientes pelo nome ou CPF.

- **Gestão de Especialidades**  
  Cadastro e listagem das especialidades médicas oferecidas pela clínica.

- **Controle de Consultas**  
  Agendamento, listagem e cancelamento de consultas, com registro de data, paciente e especialidade.

- **Limpeza de Dados**  
  Capacidade de remover todos os registros do banco de dados para reiniciar ou realizar testes.

---

## 🔑 Estrutura do Sistema

A seguir, a arquitetura do projeto, separada em camadas para facilitar a compreensão e modularidade:

```plaintext
Clinica/
├── controller/
│   ├── ConsultaController.java      # Controla operações relacionadas às consultas.
│   ├── EspecialidadeController.java # Gerencia especialidades médicas.
│   └── PacienteController.java      # Centraliza operações de pacientes.
├── model/
│   ├── Consulta.java                # Representa o modelo de consulta.
│   ├── Especialidade.java           # Modelo de especialidades médicas.
│   └── Paciente.java                # Modelo que armazena os dados dos pacientes.
├── util/
│   └── JPAUtil.java                 # Fornece a configuração e o gerenciador de entidades JPA.
├── repositories/
│   └── PacienteRepository.java      # Consulta avançada de pacientes no banco.
├── Main.java                        # Ponto de entrada da aplicação - menu principal.

```

---

## 📋 Recursos do Menu Principal

No console, os seguintes recursos estão disponíveis para o usuário:

1. **Cadastrar Paciente**: Adicione pacientes fornecendo nome, CPF, e-mail e telefone.
2. **Listar Pacientes**: Exiba uma lista de todos os pacientes cadastrados.
3. **Cadastrar Especialidade**: Registre uma nova especialidade como "Cardiologia" ou "Dermatologia".
4. **Listar Especialidades**: Exiba as especialidades médicas cadastradas.
5. **Marcar Consulta**: Agende uma consulta para um paciente e uma especialidade específica.
6. **Listar Consultas**: Mostre todas as consultas registradas, com detalhes.
7. **Editar Nome de Paciente**: Atualize o nome de um paciente cadastrado.
8. **Cancelar Consulta**: Remova uma consulta do banco de dados.
9. **Buscar Consultas por CPF**: Localize consultas realizadas para um paciente específico pelo CPF.
10. **Limpar Todos os Dados**: Apague todos os registros do banco para reiniciar o sistema.
11. **Buscar Paciente pelo Nome**: Encontre pacientes por meio de uma busca textual no nome.

---

## 💻 Tecnologias Utilizadas

- **Java**: Linguagem principal utilizada para construção da aplicação.
- **JPA (Java Persistence API)**: Para o mapeamento objeto-relacional e gerenciamento do banco de dados.
- **PostgreSQL**: Banco de dados relacional para armazenamento persistente dos dados.

---

## 🛠️ Como Configurar o Projeto

1. **Clone o repositório**
   ```bash
   git clone https://github.com/MatheusGriginii/ProjetoFaculdade.git
   ```

2. **Configuração do banco de dados**  
   Certifique-se de que o PostgreSQL esteja instalado e configurado. Depois, execute o arquivo `scripts.sql` disponível na pasta `banco-de-dados/` para criar as tabelas.

3. **Configuração do arquivo `persistence.xml`**  
   O arquivo de configuração JPA deve incluir as credenciais e nome do banco de dados correspondente. Um exemplo básico:

   ```xml
   <persistence>
       <persistence-unit name="clinicaPU">
           <properties>
               <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/nome_da_clinica"/>
               <property name="javax.persistence.jdbc.user" value="seu_usuario"/>
               <property name="javax.persistence.jdbc.password" value="sua_senha"/>
               <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
           </properties>
       </persistence-unit>
   </persistence>
   ```

4. **Executando a aplicação**  
   Compile e execute o arquivo `Main.java` para interagir com o menu principal do sistema.

---

## 📚 Estrutura das Classes e Relacionamentos

- **Paciente**  
  Contém informações como nome, CPF, e-mail e telefone. É utilizado em várias operações, como cadastro, edição e consulta.

- **Especialidade**  
  Reúne as áreas médicas que a clínica oferece. Está relacionada diretamente aos pacientes e às consultas.

- **Consulta**  
  Fundamental para o sistema, esta entidade conecta um paciente e uma especialidade. Inclui informações sobre a data e horário.

---

## ✨ Diferenciais do Projeto

- **Interface Simplificada**  
  Por ser uma aplicação de console, o foco é na eficiência operacional. Todas as opções são acessíveis por meio de menus interativos.

- **Uso de JPA com PostgreSQL**  
  A integração facilita a manipulação e persistência de dados, evitando complexidade adicional no tratamento de banco.

- **Fácil Expansão e Customização**  
  Com código modular e organizado, fica simples adicionar novos recursos ou modificar os existentes.

---

## 👨‍💻 Desenvolvedores'

Por:

- **Matheus**  
  🌐 [Github](https://github.com/MatheusGriginii)

- **João**  
  🌐 [Github](https://github.com/jo-4o)

---

