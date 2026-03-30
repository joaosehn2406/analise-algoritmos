package adapter;

import br.furb.analise.algoritmos.LampadaPhellipes;
import interfaces.ILampada;

public class LampadaPhellipesAdapter implements ILampada {

    private final LampadaPhellipes lampadaPhellipes;

    public LampadaPhellipesAdapter(LampadaPhellipes lampadaPhellipes) {

        if (lampadaPhellipes == null) {
            throw new IllegalArgumentException("Não pode ser nulo.");
        }

        this.lampadaPhellipes = lampadaPhellipes;
    }

    @Override
    public void ligar() {
        lampadaPhellipes.setIntensidade(100);
    }

    @Override
    public void desligar() {
        lampadaPhellipes.setIntensidade(0);
    }
}