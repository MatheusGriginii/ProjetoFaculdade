package src;

public class Room {
    private int number;
    private String type;
    private double price;

    // construtor com parametro
    public Room(int number, String type, Double price){
        this.number = number;
        this.type = type;
        this.price = price;
    }
    // metodos get e set
    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return String.format("Quarto #%d | Tipo: %s | Preco: R$ %.2f", number, type, price);
        
    }
}