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
    private MenuButton playButton;
    private MenuButton helpButton;
    private MenuButton exitButton;
    private int mx;
    private int my;

    public Menu() {
        loadFont();
        playButton = new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 2, 300, 100, "Play");
        helpButton = new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 3, 300, 100, "Help");
        exitButton = new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 4, 300, 100, "Exit");
    }

    private void loadFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/com/rekaerst/pong/resources/3270-Regular.otf"));
            font = font.deriveFont(Font.BOLD, 50f);
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            if (Game.isWindows()) {
                System.err.println("can't find 3270 Font. Fallback to arial");
                font = new Font("arial", Font.BOLD, 50);
            } else if (Game.isLinux()) {
                System.err.println("can't find 3270 Font. Fallback to DejaVu Sans");
                font = new Font("DejaVu Sans", Font.BOLD, 50);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        mx = e.getX();
        my = e.getY();
        System.out.println("MousePressed");
        if (isButtonClicked(playButton)) {
            System.out.println("playButton Clicked");
        }

    }

    private boolean isButtonClicked(MenuButton menuButton) {
        if (mx >= menuButton.getX() - menuButton.getWidth() / 2 && mx <= menuButton.getX() + menuButton.getWidth() / 2
                && my >= menuButton.getY() - menuButton.getHeight() / 2
                && my <= menuButton.getY() + menuButton.getHeight() / 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    public void tick() {

    }

    public void render(Graphics g) {

        // if (Game.isDebugging) {
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Menu", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Menu") / 2, 100);

        playButton.render(g);
        helpButton.render(g);
        exitButton.render(g);
        // }
    }
}
