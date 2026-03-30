package main.interfaces;

import main.models.Acao;
import main.models.Dinheiro;

public interface ObservadorAcao {

    void notificarAlteracao(Acao acao, Dinheiro valorAnterior, Dinheiro novoValor);
}
