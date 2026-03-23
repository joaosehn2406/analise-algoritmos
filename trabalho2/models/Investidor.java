package models;

import java.util.ArrayList;
import java.util.List;

public class Investidor {

    private String nome;

    private List<Ordem> ordens = new ArrayList<>();

    public Investidor(String nome) {

        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        this.nome = nome;
    }

    public void cadastrarOrdem(Ordem ordem) {
        if (ordens.isEmpty()) {
            throw new IllegalArgumentException("Ordem não pode ser vazia");
        }

        ordens.add(ordem);
    }

    public List<Ordem> getOrdens() {
        return ordens;
    }

    public String getNome() {
        return nome;
    }
}
