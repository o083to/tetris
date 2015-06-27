package bykova.tetris.model;

public class Square {

    private int x;
    private int y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Square add(Square s) {
        return new Square(this.x + s.getX(), this.y + s.getY());
    }

    public Square rotate(Square center, int a, int b) {
        int x0 = center.getX();
        int y0 = center.getY();
        int newX = x0 + a * (this.y - y0);
        int newY = y0 + b * (this.x - x0);
        return new Square(newX, newY);
    }
}
