package bykova.tetris.model;

import java.util.*;

public class Shape extends Observable {

    private static final Map<ShapeType, List<Square>> OFFSETS = new HashMap<>(7);
    static {
        OFFSETS.put(ShapeType.I, Arrays.asList(new Square(-1, 0), new Square(1, 0), new Square(2, 0)));
        OFFSETS.put(ShapeType.J, Arrays.asList(new Square(-1, -1), new Square(-1, 0), new Square(1, 0)));
        OFFSETS.put(ShapeType.L, Arrays.asList(new Square(-1, 0), new Square(1, 0), new Square(1, -1)));
        OFFSETS.put(ShapeType.O, Arrays.asList(new Square(1, 0), new Square(0, -1), new Square(1, -1)));
        OFFSETS.put(ShapeType.S, Arrays.asList(new Square(-1, 0), new Square(0, -1), new Square(1, -1)));
        OFFSETS.put(ShapeType.T, Arrays.asList(new Square(-1, 0), new Square(0, -1), new Square(1, 0)));
        OFFSETS.put(ShapeType.Z, Arrays.asList(new Square(-1, -1), new Square(0, -1), new Square(1, 0)));
    }

    private final ShapeType type;
    private List<Square> squares;
    private final Game game;

    public Shape(Game game, ShapeType type, Square center) {
        this.game = game;
        this.type = type;
        this.squares = createSquares(center, type);
    }

    public List<Square> getSquares() {
        return squares;
    }

    public ShapeType getType() {
        return type;
    }

    public void moveDown() {
        if (canMoveDown()) {
            move(0, 1);
        }
    }

    private void move(int dx, int dy) {
        for (int i = 0; i < squares.size(); i++) {
            Square oldSquare = squares.get(i);
            squares.set(i, new Square(oldSquare.getX() + dx, oldSquare.getY() + dy));
        }
        setChanged();
        notifyObservers();
    }

    public void moveLeft() {
        if (canMoveLeft()) {
            move(-1, 0);
        }
    }

    public void moveRight() {
        if (canMoveRight()) {
            move(1, 0);
        }
    }

    public void rotateLeft() {
        if (type == ShapeType.O) return;
        rotate(1, -1);
    }

    public void rotateRight() {
        if (type == ShapeType.O) return;
        rotate(-1, 1);
    }

    private void rotate(int a, int b) {
        List<Square> newSquares = new ArrayList<>(4);
        Square center = squares.get(0);
        newSquares.add(center);
        for (int i = 1; i < 4; i++) {
            Square old = squares.get(i);
            Square newSquare = old.rotate(center, a, b);
            if (!checkBounds(newSquare.getX(), newSquare.getY())) return;
            if (game.isFreeSquare(newSquare.getX(), newSquare.getY())) {
                newSquares.add(newSquare);
            } else {
                return;
            }
        }
        this.squares = newSquares;
        setChanged();
        notifyObservers();
    }

    private boolean checkBounds(int x, int y) {
        return (x >= 0 && x < Board.WIDTH) && (y >= 0 && y < Board.HEIGHT);
    }

    private boolean canMove(int dx, int dy) {
        for (Square square : squares) {
            int x = square.getX() + dx;
            int y = square.getY() + dy;
            if (!checkBounds(x, y) || !game.isFreeSquare(x, y)) {
                return false;
            }
        }
        return true;
    }

    private boolean canMoveDown() {
        return canMove(0, 1);
    }

    private boolean canMoveLeft() {
        return canMove(-1, 0);
    }

    private boolean canMoveRight() {
        return canMove(1, 0);
    }

    private static List<Square> createSquares(Square center, ShapeType type) {
        List<Square> result = new ArrayList<>(4);
        result.add(center); // The shape rotates around this square
        for (Square offset : OFFSETS.get(type)) {
            result.add(center.add(offset));
        }
        return result;
    }
}
