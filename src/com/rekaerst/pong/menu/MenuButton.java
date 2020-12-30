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

    public boolean isPressed() {
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
