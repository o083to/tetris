package bykova.tetris.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

public class Game extends Observable implements ActionListener {

    private static final int DELAY = 500;
    private static final int SPEED_UP_DELAY = 40;
    private static final int SHAPE_TYPES_COUNT = 7;
    private static final int START_SHAPE_X = 4;
    private static final int START_SHAPE_Y = 0;

    private static final Map<Integer, Integer> POINTS = new HashMap<>(4);
    static {
        POINTS.put(1, 1);
        POINTS.put(2, 3);
        POINTS.put(3, 7);
        POINTS.put(4, 15);
    }

    private final Timer timer;
    private final Board board;
    private Shape currentShape;
    private Random random = new Random();
    private Observer boardObserver;
    private int score;

    private boolean isNew = true;
    private boolean shapeFell;
    private boolean isSpeedUp;

    public Game() {
        this.board = new Board();
        tryToSetNewShape();
        this.timer = new Timer(DELAY, this);
    }

    public Board getBoard() {
        return board;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public int getScore() {
        return score;
    }

    public void setBoardObserver(Observer observer) {
        this.boardObserver = observer;
    }

    public void start() {
        if (isNew) {
            isNew = false;
            currentShape.addObserver(boardObserver);
            board.addObserver(boardObserver);
        }
        timer.start();
    }

    public void stop() {
        timer.stop();
        setChanged();
        notifyObservers(GameEvent.GameFinished);
    }

    public void speedUp() {
        if (shapeFell) return;
        isSpeedUp = true;
        timer.setDelay(SPEED_UP_DELAY);
    }

    private void resetSpeed() {
        isSpeedUp = false;
        timer.setDelay(DELAY);
    }

    private void addPoints(int points) {
        score += points;
        setChanged();
        notifyObservers(GameEvent.ScoreUpdated);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (shapeFell) {
            int count = board.removeFullRows();
            if (count != 0) addPoints(POINTS.get(count));
            tryToSetNewShape();
            currentShape.addObserver(boardObserver);
            shapeFell = false;
        } else {
            currentShape.moveDown();
            if (isFallingFinished()) {
                if (isSpeedUp) {
                    resetSpeed();
                }
                board.addFallenShape(currentShape);
                shapeFell = true;
            }
        }
    }

    public boolean isFreeSquare(int x, int y) {
        return !board.isSquareFilled(x, y);
    }

    private boolean isFallingFinished() {
        for (Square square : currentShape.getSquares()) {
            int y = square.getY();
            if (y == Board.MAX_Y || board.isSquareFilled(square.getX(), y + 1)) {
                return true;
            }
        }
        return false;
    }

    private void tryToSetNewShape() {
        Shape shape = new Shape(
                this,
                ShapeType.fromInteger(random.nextInt(SHAPE_TYPES_COUNT) + 1),
                new Square(START_SHAPE_X, START_SHAPE_Y)
        );
        for (Square square : shape.getSquares()) {
            int y = square.getY();
            if (y >= 0 && board.isSquareFilled(square.getX(), y)) {
                stop();
                return;
            }
        }
        currentShape = shape;
    }
}
