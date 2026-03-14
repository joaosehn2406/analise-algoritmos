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
}
