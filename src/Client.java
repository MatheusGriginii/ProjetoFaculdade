package src;

public class Client extends Person {

    public Client(String nome, String nr) {
        super(nome, nr); 
     } 

    @Override
    public String toString() {
        return String.format("Hospede: %s | Numero de Registro: %s", nome, nr);
    }
}
