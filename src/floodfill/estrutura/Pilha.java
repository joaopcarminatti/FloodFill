package floodfill.estrutura;

import floodfill.model.Ponto;

/**
 * Pilha propria (LIFO). Insercao e remocao sempre no topo, como pratos empilhados.
 */
public class Pilha implements EstruturaDePontos {

    private No topo;
    private int tamanho;

    public Pilha() {
        this.topo = null;
        this.tamanho = 0;
    }

    @Override
    public void adicionar(Ponto ponto) {
        No novo = new No(ponto);
        novo.setProximo(topo); // o novo no aponta para quem era o topo
        topo = novo;           // e assume o lugar dele
        tamanho++;
    }

    @Override
    public Ponto remover() {
        if (estaVazia()) {
            throw new EstruturaVaziaException("Tentativa de remover de uma pilha vazia.");
        }
        Ponto ponto = topo.getPonto();
        topo = topo.getProximo();
        tamanho--;
        return ponto;
    }

    @Override
    public boolean estaVazia() { return topo == null; }

    @Override
    public int getTamanho() { return tamanho; }

    @Override
    public String getNome() { return "Pilha"; }
}