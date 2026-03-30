package adapter;

import interfaces.ILampada;
import model.LampadaPhellipes;

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
        if (lampadaPhellipes.getIntensidade() == 0) {

            lampadaPhellipes.setIntensidade(100);
        }
    }

    @Override
    public void desligar() {
        if (lampadaPhellipes.getIntensidade() == 100) {
            lampadaPhellipes.setIntensidade(0);
        }
    }
}