package floodfill.estrutura;

import floodfill.model.Ponto;

/**
 * Fila propria (FIFO). Insere no fim, remove do inicio.
 * O ponteiro "fim" deixa a insercao em O(1); sem ele seria preciso
 * percorrer a corrente inteira a cada insercao.
 */
public class Fila implements EstruturaDePontos {

    private No inicio;
    private No fim;
    private int tamanho;

    public Fila() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public void adicionar(Ponto ponto) {
        No novo = new No(ponto);
        if (estaVazia()) {
            inicio = novo; // primeiro elemento: inicio e fim sao o mesmo no
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }
        tamanho++;
    }

    @Override
    public Ponto remover() {
        if (estaVazia()) {
            throw new EstruturaVaziaException("Tentativa de remover de uma fila vazia.");
        }
        Ponto ponto = inicio.getPonto();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null; // removeu o ultimo: o fim nao pode apontar para um no fantasma
        }
        tamanho--;
        return ponto;
    }

    @Override
    public boolean estaVazia() { return inicio == null; }

    @Override
    public int getTamanho() { return tamanho; }

    @Override
    public String getNome() { return "Fila"; }
}