package model;

import org.junit.jupiter.api.Test;
import services.PedidoService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModelTest {

    @Test
    public void cobrancaPacAte1000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(0.5);

        assertEquals(10.0, frete, 0.001);
    }

    @Test
    public void cobrancaPacDe1000Ate2000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(1.5);

        assertEquals(15.0, frete, 0.001);
    }

    @Test
    public void cobrancaPacAcimaDe2000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(2.5);

        assertEquals(0.0, frete, 0.001);
    }

    @Test
    public void cobrancaSedexAte500g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(0.4);

        assertEquals(12.5, frete, 0.001);
    }

    @Test
    public void cobrancaSedexDe500Ate1000g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(0.8);

        assertEquals(20.0, frete, 0.001);
    }

    @Test
    public void cobrancaSedexAcimaDe1000g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(1.2);

        assertEquals(49.5, frete, 0.001);
    }

    @Test
    public void cobrancaRetiradaSemCusto() {
        EntregaRetiradaEmLoja entrega = new EntregaRetiradaEmLoja();

        double frete = entrega.calcularFrete(2.0);

        assertEquals(0.0, frete, 0.001);
    }

    @Test
    public void produtoDeveSerCriadoComValoresValidos() {
        Produto produto = new Produto("Livro", 59.90, 0.8);

        assertEquals("Livro", produto.nome());
        assertEquals(59.90, produto.valor(), 0.001);
        assertEquals(0.8, produto.peso(), 0.001);
    }

    @Test
    public void produtoNaoDevePermitirNomeNulo() {
        assertThrows(NullPointerException.class, () -> new Produto(null, 10.0, 1.0));
    }

    @Test
    public void produtoNaoDevePermitirNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("   ", 10.0, 1.0));
    }

    @Test
    public void produtoNaoDevePermitirValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Livro", -1.0, 1.0));
    }

    @Test
    public void produtoNaoDevePermitirPesoZeroOuNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Livro", 10.0, 0.0));
    }

    @Test
    public void pedidoDeveAdicionarProdutoECalcularPesoTotal() {
        PedidoService pedidoService = new PedidoService();
        Pedido pedido = new Pedido(1, TipoEntrega.RETIRADA_LOJA, LocalDate.now());
        Produto p1 = new Produto("Livro A", 50.0, 0.7);
        Produto p2 = new Produto("Livro B", 70.0, 1.3);

        pedidoService.adicionarProduto(pedido, p1);
        pedidoService.adicionarProduto(pedido, p2);

        assertEquals(2.0, pedidoService.calcularPesoTotal(pedido), 0.001);
    }

    @Test
    public void pedidoDeveCalcularFreteComEstrategiaSedex() {
        PedidoService pedidoService = new PedidoService();
        Pedido pedido = new Pedido(1, TipoEntrega.SEDEX, LocalDate.now());
        Produto produto = new Produto("Livro", 80.0, 1.2);

        pedidoService.adicionarProduto(pedido, produto);

        assertEquals(49.5, pedidoService.calcularFrete(pedido), 0.001);
    }

    @Test
    public void pedidoNaoDevePermitirIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Pedido(0, TipoEntrega.SEDEX, LocalDate.now()));
    }

    @Test
    public void pedidoNaoDevePermitirProdutoNulo() {
        PedidoService pedidoService = new PedidoService();
        Pedido pedido = new Pedido(1, TipoEntrega.SEDEX, LocalDate.now());
        assertThrows(NullPointerException.class, () -> pedidoService.adicionarProduto(pedido, null));
    }
}
