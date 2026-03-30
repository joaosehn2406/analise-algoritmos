package model;

public class PersianaNatLight {
    private boolean palhetaAberta;
    private boolean palhetaErguida;

    public PersianaNatLight() {
        palhetaAberta = true;
        palhetaErguida = true;
    }

    public void descerPalheta() {
        palhetaErguida = false;
    }

    public void subirPalheta() throws Exception {
        if (!palhetaAberta) {
            throw new Exception("Palheta deve estar aberta pare subir a persiana");
        }
        palhetaErguida = true;
    }

    public void abrirPalheta() {
        palhetaAberta = true;
    }

    public void fecharPalheta() throws Exception {
        if (palhetaErguida) {
            throw new Exception("Palheta não pode ser fechada com a persiana erguida");
        }
        palhetaAberta = false;
    }

    public boolean estaPalhetaAberta() {
        return palhetaAberta;
    }

    public boolean estaPalhetaErguida() {
        return palhetaErguida;
    }
}
