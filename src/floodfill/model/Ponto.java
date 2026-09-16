package floodfill.model;

public class Ponto {

    private final int x;
    private final int y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}