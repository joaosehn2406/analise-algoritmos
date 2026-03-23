package tests;

import enums.TipoOrdem;
import interfaces.ObservadorAcao;
import models.Acao;
import models.Dinheiro;
import models.Investidor;
import models.Ordem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoCompletoTest {

    @Test
    void enumTipoOrdemDeveConterCompraEVenda() {
        assertEquals(TipoOrdem.COMPRA, TipoOrdem.valueOf("COMPRA"));
        assertEquals(TipoOrdem.VENDA, TipoOrdem.valueOf("VENDA"));
    }

    @Test
    void dinheiroDeveValidarEntradaECompararPorValor() {
        assertThrows(IllegalArgumentException.class, () -> new Dinheiro(null));
        assertThrows(IllegalArgumentException.class, () -> new Dinheiro(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new Dinheiro(new BigDecimal("-1")));

        Dinheiro d1 = dinheiro("10.0");
        Dinheiro d2 = dinheiro("10.00");
        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void ordemDeveValidarConstrutorEOperacoesBasicas() {
        Investidor investidor = new Investidor("Ana");
        Dinheiro valor = dinheiro("100.00");

        assertThrows(NullPointerException.class, () -> new Ordem(null, TipoOrdem.COMPRA, valor));
        assertThrows(NullPointerException.class, () -> new Ordem(investidor, null, valor));
        assertThrows(NullPointerException.class, () -> new Ordem(investidor, TipoOrdem.COMPRA, null));

        Ordem compra = new Ordem(investidor, TipoOrdem.COMPRA, valor);
        Ordem vendaMesmoValor = new Ordem(investidor, TipoOrdem.VENDA, dinheiro("100.0"));
        Ordem vendaOutroValor = new Ordem(investidor, TipoOrdem.VENDA, dinheiro("101.0"));

        assertTrue(compra.tiposSaoOpostos(vendaMesmoValor));
        assertFalse(compra.tiposSaoOpostos(null));
        assertTrue(compra.possuiMesmoValor(dinheiro("100.00")));
        assertFalse(compra.possuiMesmoValor(vendaOutroValor.getValor()));
        assertFalse(compra.possuiMesmoValor(null));
    }

    @Test
    void acaoDeveValidarConstrutorERegistrarOrdensSemMatch() {
        assertThrows(IllegalArgumentException.class, () -> new Acao("", dinheiro("10.00")));
        assertThrows(IllegalArgumentException.class, () -> new Acao("   ", dinheiro("10.00")));
        assertThrows(NullPointerException.class, () -> new Acao("PETR4", null));

        Acao acao = new Acao("PETR4", dinheiro("20.00"));
        Investidor investidor = new Investidor("Bruno");
        Ordem ordem = new Ordem(investidor, TipoOrdem.COMPRA, dinheiro("21.00"));

        assertFalse(acao.registrarOrdem(ordem));
        assertEquals(1, acao.getOrdens().size());
        assertThrows(NullPointerException.class, () -> acao.registrarOrdem(null));
    }

    @Test
    void acaoDeveExecutarMatchAtualizarValorENotificarObservadores() {
        Acao acao = new Acao("VALE3", dinheiro("50.00"));
        Investidor comprador = new Investidor("Carlos");
        Investidor vendedor = new Investidor("Denise");

        comprador.registrarEmAcao(acao);
        vendedor.registrarEmAcao(acao);

        assertFalse(comprador.registrarOrdem(acao, TipoOrdem.COMPRA, dinheiro("52.00")));
        assertTrue(vendedor.registrarOrdem(acao, TipoOrdem.VENDA, dinheiro("52.0")));

        assertEquals(dinheiro("52.00"), acao.getValor());
        assertEquals(0, acao.getOrdens().size());
        assertEquals(1, comprador.getNotificacoesRecebidas().size());
        assertEquals(1, vendedor.getNotificacoesRecebidas().size());
        assertTrue(comprador.getNotificacoesRecebidas().get(0).contains("VALE3"));
    }

    @Test
    void acaoDeveRegistrarEPermitirRemoverObservadorSemDuplicidade() {
        Acao acao = new Acao("ITUB4", dinheiro("30.00"));
        ObservadorFalso observador = new ObservadorFalso();
        Investidor investidor = new Investidor("Erica");

        acao.registrarObservador(observador);
        acao.registrarObservador(observador);
        acao.registrarObservador(investidor);
        assertThrows(NullPointerException.class, () -> acao.registrarObservador(null));

        investidor.registrarOrdem(acao, TipoOrdem.COMPRA, dinheiro("31.00"));
        new Investidor("Fabio").registrarOrdem(acao, TipoOrdem.VENDA, dinheiro("31.00"));

        assertEquals(1, observador.totalNotificacoes);

        acao.removerObservador(observador);
        investidor.registrarOrdem(acao, TipoOrdem.COMPRA, dinheiro("32.00"));
        new Investidor("Gabi").registrarOrdem(acao, TipoOrdem.VENDA, dinheiro("32.00"));

        assertEquals(1, observador.totalNotificacoes);
    }

    @Test
    void investidorDeveValidarNomeGerenciarOrdensERegistroEmAcao() {
        assertThrows(IllegalArgumentException.class, () -> new Investidor(null));
        assertThrows(IllegalArgumentException.class, () -> new Investidor(""));
        assertThrows(IllegalArgumentException.class, () -> new Investidor("   "));

        Investidor investidor = new Investidor("Helena");
        Acao acao = new Acao("BBDC4", dinheiro("12.00"));

        assertThrows(NullPointerException.class, () -> investidor.cadastrarOrdem(null));
        assertThrows(NullPointerException.class, () -> investidor.registrarOrdem(null, TipoOrdem.COMPRA, dinheiro("12.0")));
        assertThrows(NullPointerException.class, () -> investidor.registrarEmAcao(null));

        assertFalse(investidor.registrarOrdem(acao, TipoOrdem.COMPRA, dinheiro("12.50")));
        assertEquals("Helena", investidor.getNome());
        assertEquals(1, investidor.getOrdens().size());
        assertThrows(UnsupportedOperationException.class, () -> investidor.getOrdens().add(new Ordem(investidor, TipoOrdem.VENDA, dinheiro("11.00"))));
        assertThrows(UnsupportedOperationException.class, () -> investidor.getNotificacoesRecebidas().add("x"));
    }

    @Test
    void acaoDeveAceitarListaInicialDeOrdensNoConstrutor() {
        Investidor investidor = new Investidor("Igor");
        Ordem ordemInicial = new Ordem(investidor, TipoOrdem.COMPRA, dinheiro("44.00"));
        List<Ordem> ordensIniciais = new ArrayList<>();
        ordensIniciais.add(ordemInicial);

        Acao acao = new Acao("ABEV3", dinheiro("43.00"), ordensIniciais);

        assertEquals(1, acao.getOrdens().size());
        assertSame(ordemInicial, acao.getOrdens().get(0));
        assertThrows(UnsupportedOperationException.class, () -> acao.getOrdens().add(ordemInicial));
    }

    private static Dinheiro dinheiro(String valor) {
        return new Dinheiro(new BigDecimal(valor));
    }

    private static class ObservadorFalso implements ObservadorAcao {
        int totalNotificacoes = 0;

        @Override
        public void notificarAlteracao(Acao acao, Dinheiro valorAnterior, Dinheiro novoValor) {
            totalNotificacoes++;
        }
    }
}