package adapter;

import interfaces.IArCondicionado;
import model.ArCondicionadoVentoBaumn;

public class ArCondicionadoVentoBaumAdapter implements IArCondicionado {

    private final ArCondicionadoVentoBaumn arCondicionadoVentoBaumn;

    public ArCondicionadoVentoBaumAdapter(ArCondicionadoVentoBaumn arCondicionadoVentoBaumn) {

        if (arCondicionadoVentoBaumn == null) {
            throw new IllegalArgumentException("Não pode ser nulo.");
        }

        this.arCondicionadoVentoBaumn = arCondicionadoVentoBaumn;
    }

    @Override
    public void ligar() {
        if (!arCondicionadoVentoBaumn.estaLigado()) {
            arCondicionadoVentoBaumn.ligar();
        }
    }

    @Override
    public void desligar() {
        if (arCondicionadoVentoBaumn.estaLigado()) {
            arCondicionadoVentoBaumn.desligar();
        }
    }

    @Override
    public void aumentarTemperatura() {
        arCondicionadoVentoBaumn.definirTemperatura(arCondicionadoVentoBaumn.getTemperatura() + 1);
    }

    @Override
    public void diminuirTemperatura() {
        arCondicionadoVentoBaumn.definirTemperatura(arCondicionadoVentoBaumn.getTemperatura() - 1);
    }

    @Override
    public void definirTemperatura(int temperatura) {
        arCondicionadoVentoBaumn.definirTemperatura(temperatura);
    }

}
