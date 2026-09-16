package floodfill.estrutura;

import floodfill.model.Ponto;

public interface EstruturaDePontos {

    void adicionar(Ponto ponto);

    Ponto remover();

    boolean estaVazia();

    int getTamanho();

    String getNome();
}