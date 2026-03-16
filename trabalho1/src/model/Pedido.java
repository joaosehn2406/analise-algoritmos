package model;

import factory.TipoDeFreteFactory;
import model.interfaces.ITipoEntrega;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private final int id;
    private final List<Produto> produtos;
    private final ITipoEntrega tipoEntrega;
    private final LocalDate dataPedido;

    public Pedido(int id, TipoEntrega tipo, LocalDate dataPedido) {
        if (id <= 0) {
            throw new IllegalArgumentException("id deve ser maior que zero");
        }

        this.id = id;
        this.produtos = new ArrayList<>();
        this.tipoEntrega = TipoDeFreteFactory.criar(tipo);
        this.dataPedido = dataPedido;
    }

    public void adicionarProduto(Produto produto) {
        Objects.requireNonNull(produto, "produto não pode ser nulo");
        this.produtos.add(produto);
    }

    public double calcularPesoTotal() {
        double pesoTotal = 0.0;
        for (Produto produto : produtos) {
            pesoTotal += produto.peso();
        }
        return pesoTotal;
    }

    public double calcularFrete() {
        return tipoEntrega.calcularFrete(calcularPesoTotal());
    }
}
