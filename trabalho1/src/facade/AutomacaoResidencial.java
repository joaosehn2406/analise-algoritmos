package facade;

import interfaces.IAutomacaoResidencial;
import interfaces.ILampada;
import interfaces.IPersiana;
import interfaces.IArCondicionado;

import java.util.List;

public class AutomacaoResidencial implements IAutomacaoResidencial {

    private static final int TEMPERATURA_MODO_TRABALHO = 25;

    private final List<ILampada> lampadas;
    private final List<IPersiana> persianas;
    private final List<IArCondicionado> arCondicionados;

    public AutomacaoResidencial(List<ILampada> lampadas, List<IPersiana> persianas, List<IArCondicionado> arCondicionados) {
        this.lampadas = lampadas;
        this.persianas = persianas;
        this.arCondicionados = arCondicionados;
    }

    @Override
    public void ativarModoSono() {
        for (ILampada lampada : lampadas) {
            lampada.desligar();
        }

        for (IPersiana persiana : persianas) {
            persiana.fechar();
        }

        for (IArCondicionado ar : arCondicionados) {
            ar.desligar();
        }
    }

    @Override
    public void ativarModoTrabalho() {
        for (ILampada lampada : lampadas) {
            lampada.ligar();
        }

        for (IPersiana persiana : persianas) {
            persiana.abrir();
        }

        for (IArCondicionado ar : arCondicionados) {
            ar.ligar();
            ar.definirTemperatura(TEMPERATURA_MODO_TRABALHO);
        }
    }
}
