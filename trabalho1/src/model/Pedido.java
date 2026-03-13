package model;

import model.interfaces.ITipoEntrega;

import java.time.LocalDate;
import java.util.List;

public record Pedido(
        int id,
        List<ItemPedido> itens,
        ITipoEntrega tipoEntrega,
        LocalDate dataPedido
) {
    public double calcularPesoTotal() {
        return itens.stream()
                .mapToDouble(item -> item.produto().peso() * item.quantidade())
                .sum();
    }

    public double calcularFreteTotal() {
        return tipoEntrega.calcularFrete(calcularPesoTotal());
    }
}
