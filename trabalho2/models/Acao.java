package models;

import java.util.ArrayList;
import java.util.List;

public class Acao {

    private String nome;
    private Dinheiro valor;
    private List<Ordem> ordens = new ArrayList<>();

    public Acao(String nome, Dinheiro valor, List<Ordem> ordens) {

        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome nao pode estar vazio");
        }

        if (valor == null) {
            throw new NullPointerException("O valor da ação precisa ser diferente de nulo");
        }

        if (ordens.isEmpty()) {
            throw new IllegalArgumentException("A lista de ordens não pode estar vazia");
        }

        this.nome = nome;
        this.valor = valor;
        this.ordens = ordens;
    }


}
