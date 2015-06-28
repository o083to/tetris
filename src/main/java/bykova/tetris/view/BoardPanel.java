package bykova.tetris.view;

import bykova.tetris.model.*;
import bykova.tetris.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BoardPanel extends JPanel implements Observer {

    public static final int SQUARE_SIZE = 32;
    public static final Color BACKGROUND_COLOR = new Color(66, 66, 66);
    public static final Map<ShapeType, Color> SHAPE_COLORS = new HashMap<>(8);
    static {
        SHAPE_COLORS.put(ShapeType.EMPTY, BACKGROUND_COLOR);
        SHAPE_COLORS.put(ShapeType.I, new Color(238, 44, 44));
        SHAPE_COLORS.put(ShapeType.J, new Color(255, 140, 0));
        SHAPE_COLORS.put(ShapeType.L, new Color(255, 215, 0));
        SHAPE_COLORS.put(ShapeType.O, new Color(102, 205, 0));
        SHAPE_COLORS.put(ShapeType.S, new Color(102, 205, 170));
        SHAPE_COLORS.put(ShapeType.T, new Color(100, 149, 237));
        SHAPE_COLORS.put(ShapeType.Z, new Color(191, 62, 255));
    }

    private final Game game;

    public BoardPanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(Board.WIDTH * SQUARE_SIZE, Board.HEIGHT * SQUARE_SIZE));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        paintBoard(g2d);
        paintCurrentSquare(g2d);
    }

    private void paintBoard(Graphics2D g2d) {
        Board board = game.getBoard();
        if (board != null) {
            ShapeType[][] shapeTypes = board.getSquares();
            for (int x = 0; x < Board.WIDTH; x++) {
                for (int y = 0; y < Board.HEIGHT; y++) {
                    Color color = SHAPE_COLORS.get(shapeTypes[x][y]);
                    paintSquare(g2d, x * SQUARE_SIZE, y * SQUARE_SIZE, color);
                }
            }
        }
    }

    private void paintCurrentSquare(Graphics2D g2d) {
        Shape shape = game.getCurrentShape();
        if (shape != null) {
            Color color = SHAPE_COLORS.get(shape.getType());
            for (Square square : shape.getSquares()) {
                paintSquare(g2d, square.getX() * SQUARE_SIZE, square.getY() * SQUARE_SIZE, color);
            }
        }
    }

    private void paintSquare(Graphics2D g2d, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x + 1, y + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);

        g2d.setColor(color.brighter());
        g2d.drawLine(x, y + SQUARE_SIZE - 1, x, y);
        g2d.drawLine(x, y, x + SQUARE_SIZE - 1, y);

        g2d.setColor(color.darker());
        g2d.drawLine(x + 1, y + SQUARE_SIZE - 1, x + SQUARE_SIZE - 1, y + SQUARE_SIZE - 1);
        g2d.drawLine(x + SQUARE_SIZE - 1, y + SQUARE_SIZE - 1, x + SQUARE_SIZE - 1, y + 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
