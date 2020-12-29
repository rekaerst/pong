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

    private Image player1WinImage;
    private Image player2WinImage;

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
        ImageIcon ii = new ImageIcon("src/com/rekaerst/pong/resources/Player1Win.png");
        player1WinImage = ii.getImage();
        ii = new ImageIcon("src/com/rekaesrt/pong/resources/PlayerWin.png");
        player2WinImage = ii.getImage();
    }

    public void tick() {

    }

    public void render(Graphics g) {
        drawNumber(g, Game.WIDTH / 2 - PD_OFFSET - PD_WIDTH / 2, 50, PD_WIDTH, PD_HEIGHT,
                ScoreBoard.getPlayer1Points());
        drawNumber(g, Game.WIDTH / 2 + PD_OFFSET - PD_WIDTH / 2, 50, PD_WIDTH, PD_HEIGHT,
                ScoreBoard.getPlayer2Points());
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
                g.drawRect(x - i * PD_WIDTH, y, width, height);
                g.drawString(String.valueOf(digits[i]),
                        x - g.getFontMetrics().stringWidth(String.valueOf(digits[i])) / 2 + 10 - i * PD_WIDTH, y + 20);
            }
        }
    }

}
