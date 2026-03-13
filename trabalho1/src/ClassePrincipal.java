import model.*;

import java.time.LocalDate;
import java.util.List;

public class ClassePrincipal {

    public static void main(String[] args) {

        Produto produto0 = new Produto(
                "Produto1",
                100.00,
                50.0
        );

        Produto produto1 = new Produto(
                "Produto2",
                170.00,
                42.55
        );

        Produto produto2 = new Produto(
                "Produto3",
                389.53,
                325.31
        );

        Produto produto3 = new Produto(
                "Produto4",
                170.00,
                42.55
        );

        ItemPedido item0 = new ItemPedido(produto0, 2);
        ItemPedido item1 = new ItemPedido(produto1, 1);
        ItemPedido item2 = new ItemPedido(produto2, 10);
        ItemPedido item3 = new ItemPedido(produto3, 1);

        Pedido pedido = new Pedido(
                2,
                List.of(item0, item1, item2, item3),
                new EntregaEncomendaPac(),
                LocalDate.now()
        );

        Livraria livraria = new Livraria(
                1,
                "12.345.678/0001-99",
                "Livraria Central LTDA",
                "Livraria Central",
                "Blumenau",
                "SC",
                List.of(pedido)
        );

        System.out.println(livraria);
    }
}