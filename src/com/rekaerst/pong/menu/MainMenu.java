/*
 * MIT License
 *
 * Copyright (c) 2020 rekaerst
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.rekaerst.pong.menu;

import com.rekaerst.pong.Game;

/**
 * the mainMenu of game
 */
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
