package src;

public class Client {
    
    private String nome;
    private String nr;

    public Client(String nome, String nr){

        this.nome = nome;
        this.nr = nr;
    }

    public String getNome(String nome){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNR(String nr){
        return nr;
    }
    public void setNR(String nr){
        this.nr = nr;
    }

    @Override
    public String toString(){
        return String.format("Nome do Hospede #%s | Nuemero do registro: %s |", nome, nr);
        
    }
}
