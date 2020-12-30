package com.rekaerst.pong.menu;

import com.rekaerst.pong.Game;

public class MainMenu extends Menu {

    private MenuButton playButton;

    public MainMenu(String text, Game game, MenuHandler menuHandler) {
        super(text, game, menuHandler);
        playButton = new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 2, 300, 100, "Play", this) {
            @Override
            public void act() {
                game.setMenuVisiable(false);
                game.startGame();
            }
        };

        new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 3, 300, 100, "Help", this) {
            @Override
            public void act() {
                game.setHelpVisiable(true);
            }
        };

        new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 4, 300, 100, "Exit", this) {
            @Override
            public void act() {
                System.exit(0);
            }
        };

        new MenuButton(0 + 60, Game.HEIGHT - 30 / 2 - 1, 120, 30, "Debug", this) {
            @Override
            public void act() {
                Game.isDebugging = !Game.isDebugging;
            }
        };
    }

    public MenuButton getPlayButton() {
        return playButton;
    }
}
