package interfaces;

import models.Acao;
import models.Dinheiro;

public interface ObservadorAcao {

    void notificarAlteracao(Acao acao, Dinheiro valorAnterior, Dinheiro novoValor);
}
