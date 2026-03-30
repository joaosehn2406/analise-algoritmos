package adapter;

import br.furb.analise.algoritmos.PersianaSolarius;
import interfaces.IPersiana;

public class PersianaSolariusAdapter implements IPersiana {

    private final PersianaSolarius persianaSolarius;

    public PersianaSolariusAdapter(PersianaSolarius persianaSolarius) {
        if (persianaSolarius == null) {
            throw new IllegalArgumentException("Não pode ser nulo.");
        }

        this.persianaSolarius = persianaSolarius;
    }

    @Override
    public void abrir() {
        if (!persianaSolarius.estaAberta()) {
            persianaSolarius.subirPersiana();
        }
    }

    @Override
    public void fechar() {
        try {
            if (persianaSolarius.estaAberta()) {
                persianaSolarius.descerPersiana();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}