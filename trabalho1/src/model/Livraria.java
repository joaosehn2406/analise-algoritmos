package model;

import java.util.List;

public record Livraria(
        int id,
        String cnpj,
        String razao_social,
        String nome_fantasia,
        String cidade,
        String estado,
        List<Pedido> pedidos
) {
}
