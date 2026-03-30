package adapter;

import model.PersianaNatLight;
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
            if (!persianaNatLight.estaPalhetaAberta()) {
                persianaNatLight.abrirPalheta();
            }

            persianaNatLight.subirPalheta();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fechar() {
        try {
            if (persianaNatLight.estaPalhetaErguida()) {
                persianaNatLight.descerPalheta();
            }

            if (persianaNatLight.estaPalhetaAberta()) {
                persianaNatLight.fecharPalheta();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}