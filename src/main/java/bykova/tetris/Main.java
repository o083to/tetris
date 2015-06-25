package bykova.tetris;

import bykova.tetris.model.Game;
import bykova.tetris.view.GameView;

public class Main {

    public static void main(String args[]) {
        Game game = new Game();
        new GameView(game);
    }
}
