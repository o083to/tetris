package bykova.tetris.view;

import bykova.tetris.model.Board;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(139, 137, 112);

    private final JLabel scoreLabel = new JLabel("0");

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
    }

    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }
}
