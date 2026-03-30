package model;

public class LampadaPhellipes {

    private int intensidade;

    public int getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(int intensidade) throws IllegalArgumentException {
        if (intensidade < 0 || intensidade > 100) {
            throw new IllegalArgumentException("O valor da intensidade deve ser entre 0 e 100");
        }
        this.intensidade = intensidade;
    }
}
