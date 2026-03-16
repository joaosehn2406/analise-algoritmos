package model;

import java.util.Objects;

public class Produto {
    private final String nome;
    private final double valor;
    private final double peso;

    public Produto(String nome, double valor, double peso) {
        Objects.requireNonNull(nome, "nome não pode ser nulo");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("nome não pode ser vazio");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("valor não pode ser negativo");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("peso deve ser maior que zero");
        }

        this.nome = nome;
        this.valor = valor;
        this.peso = peso;
    }

    public String nome() {
        return nome;
    }

    public double valor() {
        return valor;
    }

    public double peso() {
        return peso;
    }
}
