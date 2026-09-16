package floodfill;

import floodfill.algoritmo.FloodFill;
import floodfill.estrutura.EstruturaDePontos;
import floodfill.estrutura.Fila;
import floodfill.estrutura.Pilha;
import floodfill.imagem.GerenciadorDeImagem;
import floodfill.imagem.GravadorDeFrames;
import floodfill.model.Ponto;

import java.awt.Color;
import java.io.IOException;

public class Main {

    private static final String IMAGEM_ENTRADA = "entrada/teste.png";
    private static final Ponto PONTO_INICIAL = new Ponto(5, 5);
    private static final int NOVA_COR = new Color(170, 0, 220).getRGB();
    private static final int INTERVALO_FRAMES = 40;

    public static void main(String[] args) {
        try {
            executarComEstrutura(new Pilha(), "saida/resultado_pilha.png", "saida/frames/pilha");
            executarComEstrutura(new Fila(), "saida/resultado_fila.png", "saida/frames/fila");
        } catch (IOException e) {
            System.err.println("Erro ao trabalhar com os arquivos de imagem: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Configuracao invalida: " + e.getMessage());
        }
    }

    private static void executarComEstrutura(EstruturaDePontos estrutura,
                                             String caminhoSaida,
                                             String pastaFrames) throws IOException {

        GerenciadorDeImagem imagem = new GerenciadorDeImagem(IMAGEM_ENTRADA);
        GravadorDeFrames gravador = new GravadorDeFrames(pastaFrames, INTERVALO_FRAMES);
        FloodFill floodFill = new FloodFill(imagem, estrutura, gravador);

        System.out.println("--- Executando com " + estrutura.getNome() + " ---");
        long inicio = System.currentTimeMillis();

        floodFill.executar(PONTO_INICIAL, NOVA_COR);
        imagem.salvar(caminhoSaida);

        long duracao = System.currentTimeMillis() - inicio;
        System.out.println("Pixels pintados: " + floodFill.getPixelsPintados());
        System.out.println("Frames gerados:  " + gravador.getFramesSalvos());
        System.out.println("Tempo:           " + duracao + " ms");
        System.out.println("Imagem final:    " + caminhoSaida);
        System.out.println();
    }
}