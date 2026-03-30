package model;

public class ArCondicionadoGellaKaza {
    private boolean ligado;
    private int temperatura;

    public ArCondicionadoGellaKaza() {
        ligado = false;
        temperatura = 28;
    }

    public void ativar() {
        ligado = true;
    }

    public void desativar() {
        ligado = false;
    }

    public void aumentarTemperatura() {
        if (temperatura+1 > 35) {
            throw new IllegalArgumentException("Limite de temperatura atingido 35");
        }

        temperatura++;
    }

    public void diminuirTemperatura() {
        if (temperatura-1 < 15) {
            throw new IllegalArgumentException("Limite de temperatura atingido 15");
        }

        temperatura--;
    }


    public int getTemperatura() {
        return temperatura;
    }

    public boolean estaLigado() {
        return ligado;
    }
}
