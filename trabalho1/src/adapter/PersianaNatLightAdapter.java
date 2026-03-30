package adapter;

import br.furb.analise.algoritmos.PersianaNatLight;
import interfaces.IPersiana;

public class PersianaNatLightAdapter implements IPersiana {

    private final PersianaNatLight persianaNatLight;

    public PersianaNatLightAdapter(PersianaNatLight persianaNatLight) {
        if (persianaNatLight == null) {
            throw new IllegalArgumentException("Não pode ser nulo.");
        }

        this.persianaNatLight = persianaNatLight;
    }

    @Override
    public void abrir() {
        persianaNatLight.abrirPalhetas();
        persianaNatLight.subirPalhetas();
    }

    @Override
    public void fechar() {
        persianaNatLight.descerPalhetas();
        persianaNatLight.fecharPalhetas();
    }

    @Override
    public boolean estaAberta() {
        return persianaNatLight.estaPalhetaErguida();
    }
}