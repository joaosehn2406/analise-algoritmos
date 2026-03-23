package main.models;

import main.enums.TipoOrdem;

public class Ordem {

    private final Investidor investidor;
    private final TipoOrdem tipoOrdem;
    private final Dinheiro valor;

    public Ordem(Investidor investidor, TipoOrdem tipoOrdem, Dinheiro valor) {

        if (investidor == null) {
            throw new NullPointerException("Investidor não pode estar nulo");
        }

        if (tipoOrdem == null) {
            throw new NullPointerException("Tipo da ordem não pode estar nulo");
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

    public Investidor getInvestidor() {
        return investidor;
    }

    public TipoOrdem getTipoOrdem() {
        return tipoOrdem;
    }

    public Dinheiro getValor() {
        return valor;
    }

}
