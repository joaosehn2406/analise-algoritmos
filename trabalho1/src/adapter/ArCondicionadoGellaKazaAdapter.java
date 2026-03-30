package adapter;

import interfaces.IArCondicionado;
import model.ArCondicionadoGellaKaza;

public class ArCondicionadoGellaKazaAdapter implements IArCondicionado {

    private final ArCondicionadoGellaKaza arCondicionadoGellaKaza;

    public ArCondicionadoGellaKazaAdapter(ArCondicionadoGellaKaza arCondicionadoGellaKaza) {
        if (arCondicionadoGellaKaza == null) {
            throw new IllegalArgumentException("NÃ£o pode ser nulo.");
        }

        this.arCondicionadoGellaKaza = arCondicionadoGellaKaza;
    }

    @Override
    public void ligar() {
        if (!arCondicionadoGellaKaza.estaLigado()) {
            arCondicionadoGellaKaza.ativar();
        }
    }

    @Override
    public void desligar() {
        if (arCondicionadoGellaKaza.estaLigado()) {
            arCondicionadoGellaKaza.desativar();
        }
    }

    @Override
    public void aumentarTemperatura() {
        if (arCondicionadoGellaKaza.estaLigado()) {
            arCondicionadoGellaKaza.aumentarTemperatura();
        }
    }

    @Override
    public void diminuirTemperatura() {
        if (arCondicionadoGellaKaza.estaLigado()) {
            arCondicionadoGellaKaza.diminuirTemperatura();
        }
    }

    @Override
    public void definirTemperatura(int temperatura) {

        if (temperatura < arCondicionadoGellaKaza.getTemperatura()) {
            diminuiTemperaturaAteIndicada(temperatura);
        } else {
            aumentaTemperaturaAteIndicada(temperatura);
        }

    }

    public void diminuiTemperaturaAteIndicada(int temperatura) {
        for (int temperaturaAtual = arCondicionadoGellaKaza
                .getTemperatura(); temperaturaAtual > temperatura; temperaturaAtual--) {
            arCondicionadoGellaKaza.diminuirTemperatura();
        }
    }

    public void aumentaTemperaturaAteIndicada(int temperatura) {
        for (int temperaturaAtual = arCondicionadoGellaKaza
                .getTemperatura(); temperaturaAtual < temperatura; temperaturaAtual++) {
            arCondicionadoGellaKaza.aumentarTemperatura();
        }
    }
}