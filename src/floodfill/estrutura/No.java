package floodfill.estrutura;

import floodfill.model.Ponto;

public class No {

    private final Ponto ponto;
    private No proximo;

    public No(Ponto ponto) {
        this.ponto = ponto;
        this.proximo = null;
    }

    public Ponto getPonto() { return ponto; }

    public No getProximo() { return proximo; }

    public void setProximo(No proximo) { this.proximo = proximo; }
}