package models;

import enums.TipoOrdem;

public class Ordem {

    private Investidor investidor;
    private TipoOrdem tipoOrdem;
    private Dinheiro valor;

    public Ordem(Investidor investidor, TipoOrdem tipoOrdem, Dinheiro valor) {

        if (investidor == null) {
            throw new NullPointerException("Investidor não pode estar nulo");
        }

        if (valor == null) {
            throw new NullPointerException("O valor não pode estar nulo e precisa ser maior que 0");
        }

        this.investidor = investidor;
        this.tipoOrdem = tipoOrdem;
        this.valor = valor;
    }

    public boolean tiposSaoOpostos(Ordem outraOrdem) {
        if (outraOrdem == null) {
            return false;
        }

        return this.tipoOrdem != outraOrdem.tipoOrdem;
    }

    public boolean possuiMesmoValor(Dinheiro outroValor) {
        return outroValor != null && valor.getQuantia().compareTo(outroValor.getQuantia()) == 0;
    }

}
