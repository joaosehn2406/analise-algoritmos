package model;

import java.util.List;

public record ItemPedido(
        List<Produto> produtos,
        int quantidade
) {
}
