package bykova.tetris;

import bykova.tetris.model.Game;
import bykova.tetris.view.GameView;

public class Main {

    public static void main(String args[]) {
        Game game = new Game();
        GameView gameView = new GameView(game);
        game.setBoardObserver(gameView.getBoardPanel());
        game.start();
    }
}
