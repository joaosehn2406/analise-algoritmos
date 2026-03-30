package main.java.model;


import main.java.model.interfaces.ITipoEntrega;

public class EntregaSedex implements ITipoEntrega {
    private static final double PESO_LIMITE_500G = 0.5;
    private static final double PESO_LIMITE_1KG = 1.0;
    private static final double VALOR_ATE_500G = 12.5;
    private static final double VALOR_500G_ATE_1KG = 20.0;
    private static final double VALOR_BASE_ACIMA_1KG = 46.5;
    private static final double INCREMENTO_PESO = 0.1;
    private static final double VALOR_POR_INCREMENTO = 1.5;
    
    @Override
    public double calcularFrete(double peso) {
        if (peso < PESO_LIMITE_500G) {
            return VALOR_ATE_500G;
        } else if (peso <= PESO_LIMITE_1KG) {
            return VALOR_500G_ATE_1KG;
        }
        return VALOR_BASE_ACIMA_1KG + Math.ceil((peso - PESO_LIMITE_1KG) / INCREMENTO_PESO) * VALOR_POR_INCREMENTO;
    }
}
