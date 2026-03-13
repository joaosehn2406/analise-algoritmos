package model;

import java.util.List;

public record ItemPedido(
        Produto produto,
        int quantidade
) {
}
