package model;

import model.interfaces.ITipoEntrega;

public class EntregaEncomendaPac implements ITipoEntrega {
    private static final double PESO_LIMITE_1 = 1.0;
    private static final double PESO_LIMITE_2 = 2.0;
    private static final double VALOR_ATE_1KG = 10.0;
    private static final double VALOR_1KG_ATE_2KG = 15.0;
    private static final double VALOR_PADRAO = 0.0;
    
    @Override
    public double calcularFrete(double peso) {

        if (peso < PESO_LIMITE_1) {
            return VALOR_ATE_1KG;
        } else if (peso <= PESO_LIMITE_2) {
            return VALOR_1KG_ATE_2KG;
        }
        return VALOR_PADRAO;
    }
}
