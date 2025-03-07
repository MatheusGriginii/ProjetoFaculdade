package src;

import java.util.Scanner;

/**
 * Classe principal que executa o sistema de gerenciamento de hotel.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        boolean running = true;
        while(running){
            // Exibe o menu de opções
            System.out.println("Sistema de gerenciamento de hotel");
            System.out.println("1. Adicionar quarto");
            System.out.println("2. Visualizar quartos");
            System.out.println("3. Novo hóspede");
            System.out.println("4. Visualização dos hóspedes e seus NR's");
            System.out.println("5. Sair");
            
            int choice; // Variável para armazenar a escolha do usuário
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Captura a entrada do usuário e converte para inteiro
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número entre 1 e 5.");
                continue; // Retorna ao início do loop caso a entrada seja inválida
            }

            switch(choice){
                case 1:
                    // Adicionar um novo quarto
                    int number;
                    while (true) {
                        try {
                            System.out.print("Digite o número do quarto: ");
                            number = Integer.parseInt(scanner.nextLine()); // Captura e converte para inteiro
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite um número inteiro para o quarto.");
                        }
                    }
                    
                    String type;
                    while (true) {
                        System.out.print("Digite o tipo do quarto: ");
                        type = scanner.nextLine(); // Captura a entrada do usuário
                        if (type.matches("[a-zA-Z ]+")) { // Verifica se contém apenas letras e espaços
                            break;
                        } else {
                            System.out.println("Entrada inválida! Digite apenas caracteres alfabéticos para o tipo do quarto.");
                        }
                    }
                    
                    double price;
                    while (true) {
                        try {
                            System.out.print("Digite o preço do quarto: ");
                            price = Double.parseDouble(scanner.nextLine()); // Captura e converte para double
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite um número válido para o preço do quarto.");
                        }
                    }

                    // Criação do objeto Room e adição ao hotel
                    Room room = new Room(number, type, price);
                    hotel.addRoom(room);
                    System.out.println("Quarto adicionado: " + room);
                    break;

                case 2:
                    // Exibir a lista de quartos
                    hotel.viewRooms();
                    break;

                case 3:
                    // Adicionar um novo hóspede
                    String nome;
                    while (true) {
                        try {
                            System.out.print("Digite seu nome: ");
                            nome = scanner.nextLine();
                            if (!nome.matches("[a-zA-Z ]+")) { // Verifica se contém apenas letras e espaços
                                throw new IllegalArgumentException("Entrada inválida! Digite apenas caracteres alfabéticos para o nome.");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    String nr;
                    while (true) {
                        System.out.print("Digite seu NR (somente números): ");
                        nr = scanner.nextLine();
                        if (nr.matches("\\d+")) { // Verifica se contém apenas números
                            break;
                        } else {
                            System.out.println("Entrada inválida! Digite apenas números para o NR.");
                        }
                    }

                    // Criação do objeto Client e adição ao hotel
                    Client client = new Client(nome, nr);
                    hotel.addClient(client);
                    System.out.println("Novo hóspede: " + client);
                    break;

                case 4:
                    // Exibir a lista de hóspedes e seus NR's
                    hotel.viewclients();
                    break;

                case 5:
                    // Finalizar o programa
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente de novo.");
            }
        }
        scanner.close(); // Fecha o scanner para evitar vazamento de recursos
    }
}