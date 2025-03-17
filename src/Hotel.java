package src;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Client> clients;

    public Hotel(){
        this.rooms = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
    // metodo pra adicionar um quarto
    public void addRoom(Room room){
       rooms.add(room); 
    }

    public void addClient(Client client){
        clients.add(client); 
     }

    // metodo pra ver os quartos
    public void viewRooms(){
        if (rooms.isEmpty()){
            System.out.println("\n Nenhum quarto cadastrado ainda.\n");
        } else {
            System.out.println("\n========== QUARTOS DISPONIVEIS ==========");
            for (Room room : rooms) {
                System.out.println(room);
            }
            System.out.println("===========================================");
        }
    }

    public void viewclients(){
        if (clients.isEmpty()){
            System.out.println("\n Nenhum cliente cadastrado ainda.\n");
        } else {
            System.out.println("\n========== Clientes ja cadastrados ==========");
            for (Client client : clients) {
                System.out.println(client);
            }
            System.out.println("===========================================");
        }
    }
}