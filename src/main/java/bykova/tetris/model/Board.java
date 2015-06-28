package bykova.tetris.model;

import java.util.Arrays;

public class Board {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int MAX_Y = HEIGHT - 1;

    private final ShapeType[][] squares;

    public Board() {
        squares = createSquares();
    }

    public boolean isSquareFilled(int x, int y) {
        if (y < 0) return false;                    // If the shape just appeared and we can see only part of it.
        return squares[x][y] != ShapeType.EMPTY;
    }

    public ShapeType[][] getSquares() {
        return squares;
    }

    public void addFallenShape(Shape shape) {
        ShapeType type = shape.getType();
        for (Square s : shape.getSquares()) {
            squares[s.getX()][s.getY()] = type;
        }
    }

    private static ShapeType[][] createSquares() {
        ShapeType[][] result = new ShapeType[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            Arrays.fill(result[i], ShapeType.EMPTY);
        }
        return result;
    }
}
