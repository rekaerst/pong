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

/**
 * Implement of simple menu handler, implement render methods. instances of
 * Buttons belonds to instance of Menu that was to be handled by handler was
 * added to a linked list LinkedList<MenuButton> buttons
 */
public class MenuHandler extends MouseAdapter {
    private int mx;
    private int my;
    public LinkedList<MenuButton> buttons = new LinkedList<MenuButton>();

    @Override
    /**
     * update lasted clicked position mx, my and test each button belonds to menu
     */
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

    /**
     * render each button belonds to corresponding menu
     * 
     * @param g graphics context to render with
     */
    public void render(Graphics g) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }

    /**
     * add instance of MenuButton to this handler
     * 
     * @param object instance of MnuButton
     */
    public void addButton(MenuButton object) {
        this.buttons.add(object);
    }

    /**
     * remove instance of MenuButton to this handler
     * 
     * @param object instance of MnuButton
     */
    public void removeButton(MenuButton object) {
        this.buttons.remove(object);
    }

    /**
     * get x coordinate of lasted clicked location
     * 
     * @return x coordinate of lasted clicked location
     */
    public int getMx() {
        return mx;
    }

    /**
     * get y coordinate of lasted clicked location
     * 
     * @return y coordinate of lasted clicked location
     */
    public int getMy() {
        return my;
    }

}
