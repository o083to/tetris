package bykova.tetris.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Random;
import javax.swing.Timer;

public class Game implements ActionListener {

    private static final int DELAY = 500;
    private static final int SHAPE_TYPES_COUNT = 7;
    private static final int START_SHAPE_X = 4;
    private static final int START_SHAPE_Y = 0;

    private final Timer timer;
    private final Board board;
    private Shape currentShape;
    private Random random = new Random();
    private Observer boardObserver;
    private boolean isNew = true;
    private boolean shapeFell;

    public Game() {
        this.board = new Board();
        setNewShape();
        this.timer = new Timer(DELAY, this);
    }

    public Board getBoard() {
        return board;
    }

    public Shape getCurrentShape() {
        return currentShape;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (shapeFell) {
            board.removeFullRows();
            setNewShape();
            currentShape.addObserver(boardObserver);
            shapeFell = false;
        } else {
            currentShape.moveDown();
            if (isFallingFinished()) {
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

    private void setNewShape() {
        this.currentShape = new Shape(
                this,
                ShapeType.fromInteger(random.nextInt(SHAPE_TYPES_COUNT) + 1),
                new Square(START_SHAPE_X, START_SHAPE_Y)
        );
    }
}
