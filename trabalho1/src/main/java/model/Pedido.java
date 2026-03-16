package main.java.model;

import main.java.factory.TipoDeFreteFactory;
import main.java.model.enums.TipoEntrega;
import main.java.model.interfaces.ITipoEntrega;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int id;
    private final List<Produto> produtos;
    private final ITipoEntrega tipoEntrega;
    private final LocalDate dataPedido;

    public Pedido(int id, TipoEntrega tipo, LocalDate dataPedido) {
        if (id <= 0) {
            throw new IllegalArgumentException("id deve ser maior que zero");
        }
        if (tipo == null) {
            throw new NullPointerException("tipo de entrega não pode ser nulo");
        }
        if (dataPedido == null) {
            throw new NullPointerException("data do pedido não pode ser nula");
        }

        this.id = id;
        this.produtos = new ArrayList<>();
        this.tipoEntrega = TipoDeFreteFactory.criar(tipo);
        this.dataPedido = dataPedido;
    }

    public int id() {
        return id;
    }

    public List<Produto> produtos() {
        return produtos;
    }

    public ITipoEntrega tipoEntrega() {
        return tipoEntrega;
    }

    public LocalDate dataPedido() {
        return dataPedido;
    }
}
