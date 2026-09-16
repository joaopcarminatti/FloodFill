package floodfill.imagem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Salva uma copia da imagem a cada N pixels pintados (a animacao).
 */
public class GravadorDeFrames {

    private final File pastaDestino;
    private final int intervalo;
    private int pixelsRegistrados;
    private int framesSalvos;

    public GravadorDeFrames(String pastaDestino, int intervalo) {
        if (intervalo < 1) {
            throw new IllegalArgumentException("O intervalo entre frames deve ser no minimo 1.");
        }
        this.pastaDestino = new File(pastaDestino);
        this.intervalo = intervalo;
        this.pixelsRegistrados = 0;
        this.framesSalvos = 0;
        prepararPasta();
    }

    private void prepararPasta() {
        if (!pastaDestino.exists()) {
            pastaDestino.mkdirs();
        } else {
            File[] antigos = pastaDestino.listFiles();
            if (antigos != null) {
                for (File f : antigos) {
                    f.delete(); // limpa a execucao anterior
                }
            }
        }
    }

    public void registrar(BufferedImage imagem) throws IOException {
        pixelsRegistrados++;
        if (pixelsRegistrados % intervalo == 0) {
            salvarFrame(imagem);
        }
    }

    public void registrarFrameFinal(BufferedImage imagem) throws IOException {
        salvarFrame(imagem);
    }

    private void salvarFrame(BufferedImage imagem) throws IOException {
        framesSalvos++;
        String nome = String.format("frame_%05d.png", framesSalvos);
        ImageIO.write(copiar(imagem), "png", new File(pastaDestino, nome));
    }

    /**
     * Copia profunda: sem isso todos os frames apontariam para o mesmo objeto
     * em memoria e a animacao inteira sairia com a imagem final.
     */
    private BufferedImage copiar(BufferedImage origem) {
        ColorModel modelo = origem.getColorModel();
        WritableRaster raster = origem.copyData(null);
        return new BufferedImage(modelo, raster, modelo.isAlphaPremultiplied(), null);
    }

    public int getFramesSalvos() { return framesSalvos; }
}
