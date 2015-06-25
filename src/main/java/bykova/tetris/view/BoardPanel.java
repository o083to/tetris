package bykova.tetris.view;

import bykova.tetris.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public static final int SQUARE_SIZE = 32;

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private final Board board;

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(Board.WIDTH * SQUARE_SIZE, Board.HEIGHT * SQUARE_SIZE));
        setBackground(BACKGROUND_COLOR);
    }
}
