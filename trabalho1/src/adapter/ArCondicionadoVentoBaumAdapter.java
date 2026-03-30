package adapter;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import interfaces.IArCondicionado;

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
        if (arCondicionadoVentoBaumn.)
    }

    @Override
    public void desligar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desligar'");
    }

    @Override
    public void aumentarTemperatura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aumentarTemperatura'");
    }

    @Override
    public void diminuirTemperatura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'diminuirTemperatura'");
    }

    @Override
    public void definirTemperatura(int temperatura) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'definirTemperatura'");
    }

}
