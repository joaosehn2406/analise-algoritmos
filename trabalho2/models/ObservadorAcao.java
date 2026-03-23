package models;

public interface ObservadorAcao {

    void notificarAlteracao(Acao acao, Dinheiro valorAnterior, Dinheiro novoValor);
}
