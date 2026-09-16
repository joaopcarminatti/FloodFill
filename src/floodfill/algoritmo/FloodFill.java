package floodfill.algoritmo;

import floodfill.estrutura.EstruturaDePontos;
import floodfill.imagem.GerenciadorDeImagem;
import floodfill.imagem.GravadorDeFrames;
import floodfill.model.Ponto;

import java.io.IOException;

/**
 * Recebe a estrutura pelo construtor: a mesma classe roda com Pilha ou Fila,
 * sem nenhum if verificando qual das duas esta em uso.
 */
public class FloodFill {

    private final GerenciadorDeImagem imagem;
    private final EstruturaDePontos estrutura;
    private final GravadorDeFrames gravador;
    private int pixelsPintados;

    public FloodFill(GerenciadorDeImagem imagem, EstruturaDePontos estrutura, GravadorDeFrames gravador) {
        this.imagem = imagem;
        this.estrutura = estrutura;
        this.gravador = gravador;
        this.pixelsPintados = 0;
    }

    public void executar(Ponto inicial, int novaCor) throws IOException {
        if (!imagem.dentroDosLimites(inicial)) {
            throw new IllegalArgumentException("O ponto inicial " + inicial + " esta fora da imagem.");
        }

        // A cor de fundo e lida uma unica vez, no inicio, e guardada.
        final int corDeFundo = imagem.getCor(inicial);

        if (corDeFundo == novaCor) {
            // Sem esta guarda o algoritmo nunca terminaria: o pixel seria pintado,
            // continuaria com a cor de fundo e voltaria a entrar na estrutura.
            throw new IllegalArgumentException("A nova cor e igual a cor de fundo. O preenchimento nao terminaria.");
        }

        estrutura.adicionar(inicial);

        while (!estrutura.estaVazia()) {
            Ponto atual = estrutura.remover();

            // Checagem 1: o pixel existe na imagem?
            if (!imagem.dentroDosLimites(atual)) {
                continue;
            }

            // Checagem 2: o pixel ainda tem a cor de fundo?
            // Um pixel ja pintado falha aqui, e e isso que faz o algoritmo terminar.
            if (imagem.getCor(atual) != corDeFundo) {
                continue;
            }

            imagem.setCor(atual, novaCor);
            pixelsPintados++;
            gravador.registrar(imagem.getImagem());

            // Os 4 vizinhos entram sem verificacao previa, conforme a especificacao.
            int x = atual.getX();
            int y = atual.getY();
            estrutura.adicionar(new Ponto(x, y - 1)); // cima
            estrutura.adicionar(new Ponto(x, y + 1)); // baixo
            estrutura.adicionar(new Ponto(x - 1, y)); // esquerda
            estrutura.adicionar(new Ponto(x + 1, y)); // direita
        }

        gravador.registrarFrameFinal(imagem.getImagem());
    }

    public int getPixelsPintados() { return pixelsPintados; }

    public String getNomeDaEstrutura() { return estrutura.getNome(); }
}