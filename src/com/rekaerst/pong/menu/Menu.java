package com.rekaerst.pong.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

import com.rekaerst.pong.Game;

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
public class Menu extends MouseAdapter {
    protected Font font;
    protected Font fontHeading;
    protected Font fontButton;
    private Game game;
    private String text;
    private MenuHandler menuHandler;

    public Menu(String text, Game game, MenuHandler menuHandler) {
        this.text = text;
        this.game = game;
        this.setMenuHandler(menuHandler);
        loadFont();
    }

    public MenuHandler getMenuHandler() {
        return menuHandler;
    }

    public void setMenuHandler(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
    }

    private void loadFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("res/3270-Regular.otf"));
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            if (Game.isWindows()) {
                System.err.println("can't find 3270 Font. Fallback to arial");
                font = new Font("arial", Font.BOLD, 50);
            } else if (Game.isLinux()) {
                System.err.println("can't find 3270 Font. Fallback to DejaVu Sans");
                font = new Font("DejaVu Sans", Font.BOLD, 50);
            }
        }
        fontHeading = font.deriveFont(Font.BOLD, 50f);
        fontButton = font.deriveFont(Font.BOLD, 30f);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setFont(fontHeading);
        g.setColor(Color.white);
        g.drawString(text, Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Menu") / 2, 60);
        g.setFont(fontButton);
        menuHandler.render(g);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
