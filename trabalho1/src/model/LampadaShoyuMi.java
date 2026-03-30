package model;

public class LampadaShoyuMi {
    private boolean ligada;

    public LampadaShoyuMi() {
        ligada = false;
    }

    public void ligar() {
        ligada = true;
    }

    public void desligar() {
        ligada = false;
    }

    public boolean estaLigada() {
        return ligada;
    }
}
