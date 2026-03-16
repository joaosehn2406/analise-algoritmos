package model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ModelTest {

    @Test
    public void cobrancaPacAte1000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(0.5);

        Assert.assertEquals(10.0, frete, 0.001);
    }

    @Test
    public void cobrancaPacDe1000Ate2000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(1.5);

        Assert.assertEquals(15.0, frete, 0.001);
    }

    @Test
    public void cobrancaPacAcimaDe2000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(2.5);

        Assert.assertEquals(0.0, frete, 0.001);
    }

    @Test
    public void cobrancaSedexAte500g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(0.4);

        Assert.assertEquals(12.5, frete, 0.001);
    }

    @Test
    public void cobrancaSedexDe500Ate1000g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(0.8);

        Assert.assertEquals(20.0, frete, 0.001);
    }

    @Test
    public void cobrancaSedexAcimaDe1000g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(1.2);

        Assert.assertEquals(49.5, frete, 0.001);
    }

    @Test
    public void cobrancaRetiradaSemCusto() {
        EntregaRetiradaEmLoja entrega = new EntregaRetiradaEmLoja();

        double frete = entrega.calcularFrete(2.0);

        Assert.assertEquals(0.0, frete, 0.001);
    }

    @Test
    public void produtoDeveSerCriadoComValoresValidos() {
        Produto produto = new Produto("Livro", 59.90, 0.8);

        Assert.assertEquals("Livro", produto.nome());
        Assert.assertEquals(59.90, produto.valor(), 0.001);
        Assert.assertEquals(0.8, produto.peso(), 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void produtoNaoDevePermitirNomeNulo() {
        new Produto(null, 10.0, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produtoNaoDevePermitirNomeVazio() {
        new Produto("   ", 10.0, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produtoNaoDevePermitirValorNegativo() {
        new Produto("Livro", -1.0, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produtoNaoDevePermitirPesoZeroOuNegativo() {
        new Produto("Livro", 10.0, 0.0);
    }

    @Test
    public void pedidoDeveAdicionarProdutoECalcularPesoTotal() {
        Pedido pedido = new Pedido(1, TipoEntrega.RETIRADA_LOJA, LocalDate.now());
        Produto p1 = new Produto("Livro A", 50.0, 0.7);
        Produto p2 = new Produto("Livro B", 70.0, 1.3);

        pedido.adicionarProduto(p1);
        pedido.adicionarProduto(p2);

        Assert.assertEquals(2.0, pedido.calcularPesoTotal(), 0.001);
    }

    @Test
    public void pedidoDeveCalcularFreteComEstrategiaSedex() {
        Pedido pedido = new Pedido(1, TipoEntrega.SEDEX, LocalDate.now());
        Produto produto = new Produto("Livro", 80.0, 1.2);

        pedido.adicionarProduto(produto);

        Assert.assertEquals(49.5, pedido.calcularFrete(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pedidoNaoDevePermitirIdInvalido() {
        new Pedido(0, TipoEntrega.SEDEX, LocalDate.now());
    }

    @Test(expected = NullPointerException.class)
    public void pedidoNaoDevePermitirProdutoNulo() {
        Pedido pedido = new Pedido(1, TipoEntrega.SEDEX, LocalDate.now());
        pedido.adicionarProduto(null);
    }
}
