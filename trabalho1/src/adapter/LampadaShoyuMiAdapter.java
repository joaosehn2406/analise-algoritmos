package adapter;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import interfaces.ILampada;

public class LampadaShoyuMiAdapter implements ILampada {

    private final LampadaShoyuMi lampadaShoyuMi;

    public LampadaShoyuMiAdapter(LampadaShoyuMi lampadaShoyuMi) {

        if (lampadaShoyuMi == null) {
            throw new IllegalArgumentException("Não pode ser nulo.");
        }

        this.lampadaShoyuMi = lampadaShoyuMi;
    }

    @Override
    public void ligar() {
        if (!lampadaShoyuMi.estaLigada()) {
            lampadaShoyuMi.ligar();
        }
    }

    @Override
    public void desligar() {
        if (lampadaShoyuMi.estaLigada()) {
            lampadaShoyuMi.desligar();
        }
    }
}