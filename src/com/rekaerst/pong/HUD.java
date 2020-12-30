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
package com.rekaerst.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.rekaerst.pong.gameobj.ID;

public class HUD {

    private static final int PD_OFFSET = 250; // point display offset
    private static final int PD_WIDTH = 60; // point display width
    private static final int PD_HEIGHT = PD_WIDTH / 2 * 3; // point display height
    private static final int PD_MARGIN = PD_WIDTH / 12;

    private Image WinImage;

    private static final int[][] digitRenderMap = {
            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
            { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1 } };

    public HUD() {
        loadImage();
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("res/Win.png");
        WinImage = ii.getImage();
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (ScoreBoard.getWinner() == ID.Player1) {
            g.drawImage(WinImage, 40, 40, WinImage.getWidth(null) / 2, WinImage.getHeight(null) / 2, null);
        } else if (ScoreBoard.getWinner() == ID.Player2) {
            g.drawImage(WinImage, Game.WIDTH - 150 - WinImage.getWidth(null) / 4, 40, WinImage.getWidth(null) / 2,
                    WinImage.getHeight(null) / 2, null);
        }
        drawNumber(g, Game.WIDTH / 2 - PD_OFFSET - PD_WIDTH / 2, 50, PD_WIDTH, PD_HEIGHT,
                ScoreBoard.getPlayerPoints(ID.Player1));
        drawNumber(g, Game.WIDTH / 2 + PD_OFFSET - PD_WIDTH / 2, 50, PD_WIDTH, PD_HEIGHT,
                ScoreBoard.getPlayerPoints(ID.Player2));
    }

    private void drawNumber(Graphics g, int x, int y, int width, int height, int number) {
        int[] digits = new int[2];
        int digitsWidth = 0;
        int remainder = number;
        // get digits from number
        do {
            digits[digitsWidth] = remainder % 10;
            digitsWidth++;
            remainder /= 10;
        } while (remainder != 0);

        // render each digits
        for (int i = 0; i < digitsWidth; i++) {
            g.setColor(new Color(220, 220, 255));
            for (int j = 0; j < 28; j++) {
                int row = j % 4;
                int column = j / 4;
                if (digitRenderMap[digits[i]][j] == 1) {
                    g.fillRect(x - i * PD_WIDTH + row * (width / 4) + PD_MARGIN - 20 * i,
                            y + column * (height / 7) + PD_MARGIN, width / 4 - PD_MARGIN, height / 7 - PD_MARGIN);
                }
            }
        }

        if (Game.isDebugging) {
            for (int i = 0; i < digitsWidth; i++) {
                g.setColor(Color.cyan);
                g.drawRect(x - i * PD_WIDTH - 20 * i, y, width, height);
                g.drawString(String.valueOf(digits[i]),
                        x - g.getFontMetrics().stringWidth(String.valueOf(digits[i])) / 2 + 10 - i * PD_WIDTH, y + 20);
            }
        }
    }

}
