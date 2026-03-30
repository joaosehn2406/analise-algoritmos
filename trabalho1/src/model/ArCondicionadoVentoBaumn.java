package model;

public class ArCondicionadoVentoBaumn {
    private boolean ligado;
    private int temperatura;

    public ArCondicionadoVentoBaumn() {
        ligado = false;
        temperatura = 24;
    }

    public void definirTemperatura(int temperatura) throws IllegalArgumentException {
        if (!ligado) {
            throw new IllegalArgumentException("Para definira a temperatura o aparelho deve estar ligado");
        }
        if (temperatura < 15 || temperatura > 35) {
            throw new IllegalArgumentException("Temperatura deve ser entre 15 e 35");
        }
        this.temperatura = temperatura;
    }

    public void ligar() {
        ligado = true;
    }

    public void desligar() {
        ligado = false;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public boolean estaLigado() {
        return ligado;
    }
}
