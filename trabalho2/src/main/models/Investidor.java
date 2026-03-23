package main.models;

import main.enums.TipoOrdem;
import main.interfaces.ObservadorAcao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Investidor implements ObservadorAcao {

    private final String nome;

    private final List<Ordem> ordens = new ArrayList<>();
    private final List<String> notificacoesRecebidas = new ArrayList<>();

    public Investidor(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        this.nome = nome;
    }

    public void cadastrarOrdem(Ordem ordem) {
        if (ordem == null) {
            throw new NullPointerException("Ordem não pode ser nula");
        }

        ordens.add(ordem);
    }

    public boolean registrarOrdem(Acao acao, TipoOrdem tipoOrdem, Dinheiro valor) {
        if (acao == null) {
            throw new NullPointerException("Ação não pode ser nula");
        }

        Ordem ordem = new Ordem(this, tipoOrdem, valor);
        cadastrarOrdem(ordem);
        return acao.registrarOrdem(ordem);
    }

    public void registrarEmAcao(Acao acao) {
        if (acao == null) {
            throw new NullPointerException("Ação não pode ser nula");
        }

        acao.registrarObservador(this);
    }

    @Override
    public void notificarAlteracao(Acao acao, Dinheiro valorAnterior, Dinheiro novoValor) {
        String mensagem = "Ação " + acao.getNome() + " alterou de " + valorAnterior + " para " + novoValor;
        notificacoesRecebidas.add(mensagem);
    }

    public List<Ordem> getOrdens() {
        return Collections.unmodifiableList(ordens);
    }

    public String getNome() {
        return nome;
    }

    public List<String> getNotificacoesRecebidas() {
        return Collections.unmodifiableList(notificacoesRecebidas);
    }
}
