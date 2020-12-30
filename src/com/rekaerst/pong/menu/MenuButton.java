package com.rekaerst.pong.menu;

import java.awt.Graphics;

public class MenuButton {

    private int x, y, width, height;
    private String text;
    protected Menu parentMenu;

    public MenuButton(int x, int y, int width, int height, String text, Menu parentMenu) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.parentMenu = parentMenu;
        parentMenu.getMenuHandler().addButton(this);
    }

    public void render(Graphics g) {
        g.drawRect(x - width / 2, y - height / 2, width, height);
        g.drawString(text, x - g.getFontMetrics().stringWidth(text) / 2, y + 10);
    }

    public void act() {
    }

    public boolean isClicked() {
        if (parentMenu.getMenuHandler().getMx() >= x - width / 2 && parentMenu.getMenuHandler().getMx() <= x + width / 2
                && parentMenu.getMenuHandler().getMy() >= y - height / 2
                && parentMenu.getMenuHandler().getMy() <= y + height / 2) {
            return true;
        } else {
            return false;
        }
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
