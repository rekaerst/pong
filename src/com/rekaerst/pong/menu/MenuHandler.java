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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MenuHandler extends MouseAdapter {
    private int mx;
    private int my;
    public LinkedList<MenuButton> buttons = new LinkedList<MenuButton>();

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        mx = e.getX();
        my = e.getY();

        for (int i = 0; i < buttons.size(); i++) {
            MenuButton tempButton = buttons.get(i);
            if (tempButton.isPressed()) {
                tempButton.act();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    public void render(Graphics g) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }

    public void addButton(MenuButton object) {
        this.buttons.add(object);
    }

    public void removeButton(MenuButton object) {
        this.buttons.remove(object);
    }

    public int getMx() {
        return mx;
    }

    public int getMy() {
        return my;
    }

}
