package models;

import java.math.BigDecimal;
import java.util.Objects;

public final class Dinheiro {

    private final BigDecimal quantia;

    public Dinheiro(BigDecimal quantia) {
        if (quantia == null || quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }

        this.quantia = quantia;
    }

    public boolean possuiMesmoValor(Dinheiro outroValor) {
        return outroValor != null && quantia.compareTo(outroValor.quantia) == 0;
    }

    public BigDecimal gerQuantia() {
        return quantia;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dinheiro outro)) return false;
        return quantia.compareTo(outro.quantia) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantia.stripTrailingZeros());
    }

    @Override
    public String toString() {
        return "R$" + String.format("%.2f", quantia);
    }
}
