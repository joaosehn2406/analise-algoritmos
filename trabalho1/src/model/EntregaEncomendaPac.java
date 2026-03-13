package model;

import model.interfaces.ITipoEntrega;

public class EntregaEncomendaPac implements ITipoEntrega {

    @Override
    public double calcularFrete(double peso) {

        if (peso < 1.0) {
            return  10.0;
        } else if (peso <= 2.0) {
            return 15.0;
        }

        return 0.0;
    }
}
