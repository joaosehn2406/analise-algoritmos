package adapter;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import interfaces.IArCondicionado;

public class ArCondicionadoGellaKazaAdapter implements IArCondicionado {

    private final ArCondicionadoGellaKaza arCondicionadoGellaKaza;

    public ArCondicionadoGellaKazaAdapter(ArCondicionadoGellaKaza arCondicionadoGellaKaza) {
        if (arCondicionadoGellaKaza == null) {
            throw new IllegalArgumentException("Não pode ser nulo.");
        }

        this.arCondicionadoGellaKaza = arCondicionadoGellaKaza;
    }

    @Override
    public void ligar() {
        if (!arCondicionadoGellaKaza.estaLigado()) {
            
        }
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
