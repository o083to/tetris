package bykova.tetris.view;

import bykova.tetris.model.Game;

import javax.swing.*;

public class GameView extends JFrame {

    private static final String TITLE = "Tetris";

    private final Game game;
    private final BoardPanel boardPanel;

    public GameView(Game game) {
        super(TITLE);
        this.game = game;

        this.boardPanel = new BoardPanel(game);
        Box layout = Box.createVerticalBox();
        add(layout);
        layout.add(boardPanel);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}
