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
        try {
            persianaNatLight.abrirPalheta();
            persianaNatLight.subirPalheta();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fechar() {
        try {
            persianaNatLight.descerPalheta();
            persianaNatLight.fecharPalheta();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean estaAberta() {
        return persianaNatLight.estaPalhetaErguida();
    }
}