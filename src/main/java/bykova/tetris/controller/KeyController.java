package bykova.tetris.controller;

import bykova.tetris.model.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {

    private final Game game;

    public KeyController(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT :
                game.getCurrentShape().moveLeft();
                break;
            case KeyEvent.VK_RIGHT :
                game.getCurrentShape().moveRight();
                break;
        }
    }
}
