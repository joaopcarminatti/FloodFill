package floodfill.imagem;

import floodfill.model.Ponto;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Unica classe que conhece File e BufferedImage.
 */
public class GerenciadorDeImagem {

    private final BufferedImage imagem;

    public GerenciadorDeImagem(String caminhoEntrada) throws IOException {
        File arquivo = new File(caminhoEntrada);
        if (!arquivo.exists()) {
            throw new IOException("Imagem nao encontrada: " + arquivo.getAbsolutePath());
        }
        BufferedImage original = ImageIO.read(arquivo);

        // Normaliza para TYPE_INT_RGB. Sem isso, imagens com canal alfa ou
        // paleta indexada devolvem valores de cor que nunca batem na comparacao.
        this.imagem = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = this.imagem.createGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();
    }

    public int getLargura() { return imagem.getWidth(); }

    public int getAltura() { return imagem.getHeight(); }

    /** Evita o Index Out Of Bounds citado no enunciado. */
    public boolean dentroDosLimites(Ponto ponto) {
        return ponto.getX() >= 0
                && ponto.getY() >= 0
                && ponto.getX() < getLargura()
                && ponto.getY() < getAltura();
    }

    public int getCor(Ponto ponto) {
        return imagem.getRGB(ponto.getX(), ponto.getY());
    }

    public void setCor(Ponto ponto, int cor) {
        imagem.setRGB(ponto.getX(), ponto.getY(), cor);
    }

    public BufferedImage getImagem() { return imagem; }

    public void salvar(String caminhoSaida) throws IOException {
        File arquivo = new File(caminhoSaida);
        File pasta = arquivo.getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs(); // ImageIO nao cria pasta sozinho, so falha
        }
        ImageIO.write(imagem, "png", arquivo);
    }
}