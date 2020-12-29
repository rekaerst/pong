package com.rekaerst.pong;

import java.awt.Graphics;

public class MenuButton {

    private int x, y, width, height;
    private String text;

    public MenuButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;

    }

    public void render(Graphics g) {
        g.drawRect(x - width / 2, y - height / 2, width, height);
        g.drawString(text, x - g.getFontMetrics().stringWidth(text) / 2, y + 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
