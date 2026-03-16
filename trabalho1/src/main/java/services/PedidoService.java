package services;

import model.Pedido;
import model.Produto;

import java.util.Objects;

public class PedidoService {

    public void adicionarProduto(Pedido pedido, Produto produto) {
        Objects.requireNonNull(pedido, "pedido não pode ser nulo");
        Objects.requireNonNull(produto, "produto não pode ser nulo");
        pedido.produtos().add(produto);
    }

    public double calcularPesoTotal(Pedido pedido) {
        Objects.requireNonNull(pedido, "pedido não pode ser nulo");

        double pesoTotal = 0.0;
        for (Produto produto : pedido.produtos()) {
            pesoTotal += produto.peso();
        }
        return pesoTotal;
    }

    public double calcularFrete(Pedido pedido) {
        Objects.requireNonNull(pedido, "pedido não pode ser nulo");
        return pedido.tipoEntrega().calcularFrete(calcularPesoTotal(pedido));
    }
}
