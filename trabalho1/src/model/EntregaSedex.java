package model;

import model.interfaces.ITipoEntrega;

public class EntregaSedex implements ITipoEntrega {

    @Override
    public double calcularFrete(double peso) {

        if (peso < 0.5) {
            return 12.5;
        } else if (peso <= 1.0) {
            return 20.0;
        }

        return 46.5 + (1.0 - peso % 0.1) * 1.5;
    }
}
