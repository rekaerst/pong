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
            if (tempButton.isClicked()) {
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
