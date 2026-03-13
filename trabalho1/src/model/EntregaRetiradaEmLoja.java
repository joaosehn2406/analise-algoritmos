package model;

import model.interfaces.ITipoEntrega;

public class EntregaRetiradaEmLoja implements ITipoEntrega {

    @Override
    public double calcularFrete(double peso) {
        return 0;
    }
}
