package bykova.tetris.model;

import java.util.Random;

public class Game {

    private Board board;
    private Shape currentShape;
    private Random random = new Random();

    public Game() {
        this.board = new Board();
        this.currentShape = new Shape(1 + random.nextInt(6));
    }
}
