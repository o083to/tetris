package bykova.tetris.view;

import bykova.tetris.model.Game;
import bykova.tetris.model.GameEvent;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class GameView extends JFrame implements Observer {

    private static final String TITLE = "Tetris";

    private final Game game;
    private final BoardPanel boardPanel;
    private final InfoPanel infoPanel;

    public GameView(Game game) {
        super(TITLE);
        this.game = game;

        Box layout = Box.createHorizontalBox();
        add(layout);

        boardPanel = new BoardPanel(game);
        layout.add(boardPanel);

        infoPanel = new InfoPanel();
        layout.add(infoPanel);

        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvent event = (GameEvent)arg;
        switch (event) {
            case ScoreUpdated:
                infoPanel.setScore(game.getScore());
                break;
        }
    }
}
