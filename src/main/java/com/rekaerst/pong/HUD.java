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
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.rekaerst.pong.gameobj.ID;

/**
 * implement Head-Up-Display(HUD) which display in-game information on top layer
 * of all graphical elements. implement tick,render methoda and 7 segment didgit
 * display. instance of HUD shows score of each player and show a image to
 * indicate the winner on screen.
 */
public class HUD {

    private static final int PD_OFFSET = 250; // point display offset
    private static final int PD_WIDTH = 60; // point display width
    private static final int PD_HEIGHT = PD_WIDTH / 2 * 3; // point display height
    private static final int PD_MARGIN = PD_WIDTH / 12; // margin between each "pixel" of point display

    // image to show when a player reaches target score
    private Image WinImage;

    // digit to pixels map of 8 segment digit dispaly
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
        InputStream is = getClass().getResourceAsStream("/images/Win.png");
        try {
            WinImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** tick method of HUD, handles game logic of HUD */
    public void tick() {

    }

    /**
     * render method of HUD, handles graphics rendering. HUD would draw points of
     * each player at specific position using a number display that mimic a 7
     * segment digit display. HUD would also draw a image that idicates winner of
     * cureent game of at specific position
     * 
     * @param g graphics context to rander on
     */
    public void render(Graphics g) {

        // draw image that indicates the winner if ScoreBaord.getWinner() returns non
        // null value and in range of ID.Player1 or ID.Player2
        if (ScoreBoard.getWinner() == ID.Player1) {
            g.drawImage(WinImage, 40, 40, WinImage.getWidth(null) / 2, WinImage.getHeight(null) / 2, null);
        } else if (ScoreBoard.getWinner() == ID.Player2) {
            g.drawImage(WinImage, Game.WIDTH - 150 - WinImage.getWidth(null) / 4, 40, WinImage.getWidth(null) / 2,
                    WinImage.getHeight(null) / 2, null);
        }

        // draw score of each player on screen
        drawNumber(g, Game.WIDTH / 2 - PD_OFFSET - PD_WIDTH / 2, 50, PD_WIDTH, PD_HEIGHT,
                ScoreBoard.getPlayerPoints(ID.Player1));
        drawNumber(g, Game.WIDTH / 2 + PD_OFFSET - PD_WIDTH / 2, 50, PD_WIDTH, PD_HEIGHT,
                ScoreBoard.getPlayerPoints(ID.Player2));
    }

    /**
     * darw a number
     * 
     * @param g      graphics context
     * @param x      x coordinate of digit (center)
     * @param y      y coordinate of digit (center)
     * @param width  width of digit
     * @param height height of digit
     * @param number number to draw
     */
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
