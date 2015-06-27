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
}
