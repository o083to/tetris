package bykova.tetris.model;

import java.util.Arrays;
import java.util.Observable;

public class Board extends Observable {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int MAX_Y = HEIGHT - 1;

    private final ShapeType[][] squares;

    public Board() {
        squares = createSquares();
    }

    public boolean isSquareFilled(int x, int y) {
        if ((x < 0 || x >= WIDTH) || (y < 0 || y >= HEIGHT)) {
            throw new IllegalArgumentException(
                    String.format("Square with row = %d and column = %d doesn't exist.", y, x)
            );
        }
        return squares[y][x] != ShapeType.EMPTY;
    }

    public ShapeType[][] getSquares() {
        return squares;
    }

    public void addFallenShape(Shape shape) {
        ShapeType type = shape.getType();
        for (Square s : shape.getSquares()) {
            squares[s.getY()][s.getX()] = type;
        }
    }

    private static ShapeType[][] createSquares() {
        ShapeType[][] result = new ShapeType[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            result[row] = createEmptyRow();
        }
        return result;
    }

    private static ShapeType[] createEmptyRow() {
        ShapeType[] row = new ShapeType[WIDTH];
        Arrays.fill(row, ShapeType.EMPTY);
        return row;
    }
}
