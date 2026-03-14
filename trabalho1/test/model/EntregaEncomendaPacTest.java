package model;

import org.junit.Assert;
import org.junit.Test;

public class EntregaEncomendaPacTest {

    @Test
    public void cobrancaAte1000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(0.5);

        Assert.assertEquals(10.0, frete, 0.001);
    }

    @Test
    public void cobrancaDe1000Ate2000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(1.5);

        Assert.assertEquals(15.0, frete, 0.001);
    }

    @Test
    public void cobrancaAcimaDe2000g() {
        EntregaEncomendaPac entrega = new EntregaEncomendaPac();

        double frete = entrega.calcularFrete(2.5);

        Assert.assertEquals(0.0, frete, 0.001);
    }
 }
