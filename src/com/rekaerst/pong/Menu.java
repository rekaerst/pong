package com.rekaerst.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Menu extends MouseAdapter {
    Font font;

    public Menu() {
        loadFont();
    }

    private void loadFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/com/rekaerst/pong/resources/3270.ttf"));
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            System.err.println("can't find 3270.ttf fallback to arial");
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (Game.isDebugging) {
            g.setColor(Color.white);
            g.drawString("Menu", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Menu") / 2, Game.HEIGHT / 2);

            g.drawRect(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 50, 300, 100);
        }
    }
}
