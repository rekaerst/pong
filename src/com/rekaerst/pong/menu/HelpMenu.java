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

import java.awt.Font;
import java.awt.Graphics;

import com.rekaerst.pong.Game;

/**
 * the help submenu of game
 */
public class HelpMenu extends Menu {

    private Font fontText;

    private static final String helpControl = "How to Play\n\n"
            + "Use W and S keys to move paddle of Player 1 who is \nat left side. Use Up and Down keys to move paddle of\n"
            + "Player 2 who is at right side. You can also use \n"
            + "A and D or Right and Left to control the force of\n" + "Player 1 or Player 2 to hit the ball.\n\n"
            + "The goal is for each player to reach 12 points before\n\n"
            + "the opponent; points are earned when one fails \n" + "to return the ball to the other.\n\n"
            + "You can press \"Back\" button to go back to main menu\n"
            + "to start the game, press the \"Play\" button\n" + "to exit the game press the \"Exit\" button\n\n"
            + "You go back to this menu in game by pressing ESC key on your keyboard,\n"
            + "the \"Debug\" button is intended for developers (Dont touch my garbage XD)";
    private static final String helpWiki = "A Little Wiki\n\n" + "Pong is a table tennis–themed arcade video game\n"
            + "featuring simple two-dimensional graphics,\n" + "manufactured by Atari and originally released\n"
            + "in 1972. It was one of the earliest arcade \n" + "video games; it was created by Allan Alcorn\n"
            + "as a training exercise assigned to him by\n" + "Atari co-founder Nolan Bushnell, but Bushnell and \n"
            + "Atari co-founder Ted Dabney were surprised by\n" + "the quality of Alcorn's work and decided\n"
            + "to manufacture the game.\n" + "Pong was the first commercially successful\n"
            + "video game, and it helped to establish\n" + "the video game industry.";

    public HelpMenu(String text, Game game, MenuHandler menuHandler) {
        super(text, game, menuHandler);
        new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 4, 300, 100, "Back", this) {
            @Override
            public void act() {
                game.setHelpVisiable(false);
            }
        };
        fontText = font.deriveFont(Font.CENTER_BASELINE, 20f);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setFont(fontText);
        drawText(g, helpControl, 20, 100);
        drawText(g, helpWiki, Game.WIDTH / 2 + 40, 100);

    }

    private void drawText(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

}
