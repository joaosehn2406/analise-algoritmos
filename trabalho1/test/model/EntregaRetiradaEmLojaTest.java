package model;

import org.junit.Assert;
import org.junit.Test;

public class EntregaRetiradaEmLojaTest {

    @Test
    public void cobrancaSemCusto() {
        EntregaRetiradaEmLoja entrega = new EntregaRetiradaEmLoja();

        double frete = entrega.calcularFrete(2.0);

        Assert.assertEquals(0.0, frete, 0.001);
    }
}
