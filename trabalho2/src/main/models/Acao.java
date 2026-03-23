package models;

import interfaces.ObservadorAcao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Acao {

    private final String nome;
    private Dinheiro valor;
    private final List<Ordem> ordens = new ArrayList<>();
    private final List<ObservadorAcao> observadores = new ArrayList<>();

    public Acao(String nome, Dinheiro valor) {
        this(nome, valor, new ArrayList<>());
    }

    public Acao(String nome, Dinheiro valor, List<Ordem> ordens) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome nao pode estar vazio");
        }

        if (valor == null) {
            throw new NullPointerException("O valor da ação precisa ser diferente de nulo");
        }

        this.nome = nome;
        this.valor = valor;

        if (ordens != null) {
            this.ordens.addAll(ordens);
        }
    }

    public boolean registrarOrdem(Ordem ordem) {
        if (ordem == null) {
            throw new NullPointerException("Ordem não pode ser nula");
        }

        ordens.add(ordem);
        return tentarExecutarMatch(ordem);
    }

    public void registrarObservador(ObservadorAcao observador) {
        if (observador == null) {
            throw new NullPointerException("Observador não pode ser nulo");
        }

        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void removerObservador(ObservadorAcao observador) {
        observadores.remove(observador);
    }

    public String getNome() {
        return nome;
    }

    public Dinheiro getValor() {
        return valor;
    }

    public List<Ordem> getOrdens() {
        return Collections.unmodifiableList(ordens);
    }

    private boolean tentarExecutarMatch(Ordem novaOrdem) {
        for (int i = 0; i < ordens.size(); i++) {
            Ordem ordemExistente = ordens.get(i);

            if (ordemExistente == novaOrdem) {
                continue;
            }

            if (!ordemExistente.tiposSaoOpostos(novaOrdem)) {
                continue;
            }

            if (!ordemExistente.possuiMesmoValor(novaOrdem.getValor())) {
                continue;
            }

            ordens.remove(ordemExistente);
            ordens.remove(novaOrdem);

            atualizarValor(novaOrdem.getValor());
            return true;
        }

        return false;
    }

    private void atualizarValor(Dinheiro novoValor) {
        Dinheiro valorAnterior = this.valor;
        this.valor = novoValor;
        notificarObservadores(valorAnterior, novoValor);
    }

    private void notificarObservadores(Dinheiro valorAnterior, Dinheiro novoValor) {
        for (ObservadorAcao observador : observadores) {
            observador.notificarAlteracao(this, valorAnterior, novoValor);
        }
    }

}
