package model;

import model.interfaces.TipoEntrega;

import java.time.LocalDate;
import java.util.List;

public record Pedido(
        int id,
        List<ItemPedido> itens,
        double valortotal,
        TipoEntrega tipoEntrega,
        LocalDate dataPedido
) {
}
