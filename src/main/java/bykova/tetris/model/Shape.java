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

    public boolean moveDown() {
        if (canMoveDown()) {
            move(0, 1);
            return true;
        } else {
            return false;
        }
    }

    private void move(int dx, int dy) {
        Set<Square> changedSet = new HashSet<>();
        for (int i = 0; i < squares.size(); i++) {
            Square oldSquare = squares.get(i);
            changedSet.add(oldSquare);
            Square newSquare = new Square(oldSquare.getX() + dx, oldSquare.getY() + dy);
            changedSet.add(newSquare);
            squares.set(i, newSquare);
        }
        setChanged();
        notifyObservers(changedSet);
    }

    public void moveLeft() {
        if (canMoveLeft()) {
            move(-1, 0);
        }
    }

    public void moveRight() {
        if (canMoveRight()) {
            move(-1, 0);
        }
    }

    public void rotateLeft() {
        rotate(1, -1);
    }

    public void rotateRight() {
        rotate(-1, 1);
    }

    private void rotate(int a, int b) {
        List<Square> newSquares = new ArrayList<>(4);
        Set<Square> changedSet = new HashSet<>();
        Square center = squares.get(0);
        newSquares.add(center);
        for (int i = 1; i < 4; i++) {
            Square old = squares.get(i);
            int newX = a * (old.getY() - center.getY());
            int newY = b * (old.getX() - center.getX());
            if (game.isFreeSquare(newX, newY)) {
                changedSet.add(old);
                Square newSquare = new Square(newX, newY);
                changedSet.add(newSquare);
                newSquares.add(newSquare);
            } else {
                return;
            }
        }
        this.squares = newSquares;
        setChanged();
        notifyObservers(changedSet);
    }

    private boolean canMoveDown() {
        for (Square square : squares) {
            if (!game.isFreeSquare(square.getX(), square.getY() + 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean canMoveLeft() {
        for (Square square : squares) {
            int x = square.getX();
            if (x == 0 || !game.isFreeSquare(x - 1, square.getY())) {
                return false;
            }
        }
        return true;
    }

    private boolean canMoveRight() {
        for (Square square : squares) {
            int x = square.getX();
            if ((x == Board.WIDTH - 1) || !game.isFreeSquare(x + 1, square.getY())) {
                return false;
            }
        }
        return true;
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
