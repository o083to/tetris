package bykova.tetris.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Board extends Observable {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int MAX_Y = HEIGHT - 1;

    private ShapeType[][] squares;

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
            if (s.getY() >= 0) squares[s.getY()][s.getX()] = type;
        }
        setChanged();
        notifyObservers();
    }

    public int removeFullRows() {
        Set<Integer> fullRowNumbers = getFullRowsNumbers();
        if (!fullRowNumbers.isEmpty()) {
            ShapeType[][] newSquares = new ShapeType[HEIGHT][];
            int last = MAX_Y;
            for (int sourceRow = MAX_Y; sourceRow >= 0; sourceRow--) {
                if (!fullRowNumbers.contains(sourceRow)) {
                    newSquares[last--] = squares[sourceRow];
                }
            }
            while (last >= 0) {
                newSquares[last--] = createEmptyRow();
            }
            squares = newSquares;
            setChanged();
            notifyObservers();
        }
        return fullRowNumbers.size();
    }

    private Set<Integer> getFullRowsNumbers() {
        Set<Integer> numbers = new HashSet<>();
        for (int row = 0; row < HEIGHT; row++) {
            if (rowIsFull(squares[row])) {
                numbers.add(row);
            }
        }
        return numbers;
    }

    private static boolean rowIsFull(ShapeType[] row) {
        for (ShapeType i : row) {
            if (i == ShapeType.EMPTY) return false;
        }
        return true;
    }

    private static ShapeType[][] createSquares() {
        ShapeType[][] result = new ShapeType[HEIGHT][];
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
