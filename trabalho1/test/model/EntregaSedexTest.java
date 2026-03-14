package model;

import org.junit.Assert;
import org.junit.Test;

public class EntregaSedexTest {

    @Test
    public void cobrancaDeAte500g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(0.4);

        Assert.assertEquals(12.5, frete, 0.001);
    }

    @Test
    public void cobrancaDe500Ate1000g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(0.8);

        Assert.assertEquals(20.0, frete, 0.001);
    }

    @Test
    public void cobrancaAcimaDe1000g() {
        EntregaSedex entrega = new EntregaSedex();

        double frete = entrega.calcularFrete(1.2);

        Assert.assertEquals(49.5, frete, 0.001);
    }
}