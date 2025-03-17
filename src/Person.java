package src;

public class Person {
    protected String nome;
    protected String nr; /*número de registro */

    public Person(String nome, String nr) {
        this.nome = nome;
        this.nr = nr;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Registro: %s", nome, nr);
    }
}
