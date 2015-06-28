package bykova.tetris.view;

import bykova.tetris.model.Board;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(139, 137, 112);

    private final JLabel scoreLabel = new JLabel("0");
    private final JLabel gameFinishedLabel = new JLabel();

    public InfoPanel() {
        setPreferredSize(
                new Dimension(Board.WIDTH / 2 * BoardPanel.SQUARE_SIZE, Board.HEIGHT * BoardPanel.SQUARE_SIZE)
        );
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Box mainBox = Box.createVerticalBox();
        add(mainBox);

        //todo: Должно выглядеть нормально
        mainBox.add(new JLabel("Score:"));
        mainBox.add(scoreLabel);
        mainBox.add(gameFinishedLabel);

        mainBox.add(new JLabel("Keys:"));
        mainBox.add(new JLabel("Left: move left"));
        mainBox.add(new JLabel("Right: move right"));
        mainBox.add(new JLabel("Up: rotate left"));
        mainBox.add(new JLabel("Down: rotate right"));
        mainBox.add(new JLabel("Space: drop shape"));
    }

    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public void setGameFinishedMessage() {
        gameFinishedLabel.setText("Game over.");
    }
}
